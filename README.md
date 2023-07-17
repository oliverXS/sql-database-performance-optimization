# SQL Database Performance Optimization Step by Step

## Step1 Add Index

Indexes are used in MySQL to find rows with specific column values quickly. Without an index, MySQL must begin with the first row and then read through the entire table to find the relevant rows, which is inefficient for large datasets.

### Without an index

```sql
SELECT * FROM books WHERE author = 'John Doe';
```

MySQL would need to scan the entire table to find all books written by John Doe. If there are millions of books, this operation can take a long time.

MySQL has to use a full table scan, which requires going through every row of the table one by one to find the matching rows. This operation has a time complexity of **O(n)**, where n is the number of rows in the table. This means that the time taken to execute the operation increases linearly with the size of the table.

### With an index

```sql
CREATE INDEX idx_author ON books(author);
```

With this index in place, run the same query:

```sql
SELECT * FROM books WHERE author = 'John Doe';
```

MySQL can use the idx_author index to find the books written by John Doe much more quickly. The database engine can jump directly to the relevant entries using the index, rather than scanning the entire table.

When an index is available, MySQL tends to use B-tree index. In a B-tree index, data is stored in a tree-like structure where each node is sorted. This allows MySQL to use a process similar to a binary search, which significantly reduces the number of operations needed to find a match. The search operation has a time complexity of **O(log n)**, where n is the number of entries in the index.

## Step2 Introduce Redis as cache

Although the use of index improves the query speed of mysql, a large number of requests to access MySQL during peak hours will cause the MySQL server to respond too slowly.

![redis-data-flow-chart](./images/redis-data-flow-chart.png)

In order to reduce the burden on the MySQL server, Redis is introduced as a cache. In order to make Redis achieve the desired effect, it is necessary to improve the cache hit rate as much as possible.

### Strategies to Enhance Cache Hit Rate

#### 1. Identifying Appropriate Business Scenarios

Cache systems are highly effective in scenarios where read operations significantly outnumber write operations, preferably in high-frequency access scenarios. 

#### 2. Setting the Cache Capacity Judiciously

Proper cache size allocation is vital for its optimal functioning. If the cache capacity is set too low, it will frequently trigger Redis's memory eviction mechanism, leading to key deletions and thereby neutralizing the caching effect.

#### 3. Fine-tuning Cache Expiration Time

Precise calibration of cache expiration time can prevent simultaneous invalidation of keys, which could otherwise lead all requests straight to the database, causing a cache breakdown. An incorrect expiration time setting can significantly impact cache performance.

#### 4. Mitigating Cache Penetration

When querying data, the initial check should be from the Redis cache. If not found, the database should be queried. In case the data is absent in both places, it can lead to cache penetration.

If a high volume of such requests is received, and the data is not available either in Redis or in the database, it could trigger a cache avalanche, potentially leading to a service crash.

To prevent this, an empty object can be set in the cache for such query requests, effectively returning an empty object to the requester.

#### 5. Implementing Cache Warm-Up

To enhance cache hit rate, the practice of "warming up" the cache can be employed. This process involves pre-loading relevant data from the database into the Redis cache. Thus, when a query is made for the first time, the data can be directly fetched from the cache, resulting in faster response times.

## Step3 Read/Write Splitting

![read-write-split-architecture](./images/read-write-split-architecture.png)

![read-write-split-config-flow-chart](./images/read-write-split-config-flow-chart.png)

## Step4 Database Table Horizontal Sharding and Partitioning

In Step 3, we improved the performance of data retrieval by implementing the master-slave mode. However, we have yet to find a solution to enhance the writing capability of the primary database. Therefore, in this phase, I propose horizontally sharding and partitioning the tables in the database to improve the performance of data insertion.

#### 1. Horizontal Sharding

![table-split-1](./images/table-split-1.png)

#### 2. Horizontal Partitioning

![table-split-2](./images/table-split-2.png)

#### 3. Horizontal Sharding and Partitioning

![table-split-3](./images/table-split-3.png)

Horizontal sharding and partitioning offer the advantages of increased parallelism, reduced contention, improved scalability, targeted optimization, and workload isolation, resulting in optimized write performance. By distributing data across multiple shards or partitions, concurrent writes can be achieved, minimizing contention for system resources, and enabling linear scalability. Each shard or partition can be optimized individually, allowing for tailored indexing and performance optimizations, while workload isolation prevents write-intensive operations from impacting other critical processes. 

## Step4 Router Policy and Global ID in a Multi-Database and Multi-Table Environment

### Scenarios for Multi-Table Queries

![router-policy-1](./images/router-policy-1.png)

After an order is created, from the user's perspective, there is a need to query the order and check its status. From the merchant's perspective, there is a requirement to retrieve order information and analyze the orders.

![router-policy-2](./images/router-policy-2.png)

However, in a multi-table environment, when a user places an order, the created orders may be stored in different tables, requiring queries to involve multiple tables.

Therefore, to improve query efficiency, it is necessary to establish a routing rule that ensures orders created by the same user are stored in the same table as much as possible.

### Router Policy for User (toC)

#### 1. Start from a Multi-table environment

![router-policy-3](./images/router-policy-3.png)

Since the user_id is unique, it can be used as the routing key in this scenario. When a user places an order and the order is generated, the user_id is hashed, and then the resulting value is used to perform modulo operation with the number of tables. This ensures that the order information for the same user is stored in the same table.

#### 2. Expand to a Multi-database and Multi-table environment

Following the previous approach, first locate the database containing the order information, and then find the corresponding table within it.

![router-policy-4](./images/router-policy-4.png)

User places an order -> Generate an order -> Use the hash value of user_id to perform modulo operation with the number of databases to locate the corresponding database -> Divide the hash value of user_id by the number of tables and perform modulo operation to locate the corresponding table.

### Router Policy for Merchant (toB)

The merchant should be able to retrieve the corresponding user's order information. However, the merchant should not use user_id as the routing key. This is not reasonable.

Therefore, from the user's perspective, use user_id as the routing key. From the merchant's perspective, use merchant_id as the routing key.。

![router-policy-5](./images/router-policy-5.png)

Introduce RocketMQ as the message queue. When a user places an order, the order number is sent to the MQ. The merchant consumes this MQ message, retrieves the order information based on the order number, and then inserts the order information into the user's database.

### Generate globally unique IDs for order table

Introduce the SnowFlake algorithm.

![snowflake](./images/snowflake.png)

Snowflake algorithm can generate globally unique order IDs based on different servers and timestamps.

### Optimize the Scalability of Database Servers

Introduce consistent hash algorithm to replace the hash algorithm for the fixed number of servers.

[More details about Consistent Hashing] [Ref]

[Ref]: (https://github.com/oliverXS/consistent-hashing-java



![consistant-hashing](./images/consistant-hashing.png)

#### 1. Data migration

Set the database server on a hash ring of length 2^32 -1. When the server increases or decreases, the amount of data that needs to be migrated is greatly reduced.

#### 2. Load balancing

Introduce virtual nodes on the hash ring to ensure load balancing and avoid a large number of requests to access the same server.
