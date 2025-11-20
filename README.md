# Task Scheduler Platform

A distributed task scheduling platform built with Spring Boot, Apache Flink, Kafka, RocksDB, and Cassandra.

## Features

- Create, schedule, and manage tasks with cron expressions
- Distributed task execution using Apache Flink
- Event-driven architecture with Kafka
- Durable state management with RocksDB
- Scalable storage with Cassandra
- REST API for task management
- Retry mechanism for failed tasks

## Prerequisites

- Java 17+
- Apache Kafka
- Apache Cassandra
- Maven

## Getting Started

1. **Start Kafka and Cassandra**
   ```bash
   # Start Zookeeper
   bin/zookeeper-server-start.sh config/zookeeper.properties
   
   # Start Kafka
   bin/kafka-server-start.sh config/server.properties
   
   # Start Cassandra
   cassandra -f
   ```

2. **Create the Cassandra keyspace**
   ```sql
   CREATE KEYSPACE IF NOT EXISTS taskscheduler 
   WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
   ```

3. **Build and run the application**
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

- `POST /api/tasks` - Create a new task
- `GET /api/tasks/{id}` - Get task details
- `POST /api/tasks/{id}/cancel` - Cancel a task
- `POST /api/tasks/{id}/retry` - Retry a failed task

## Configuration

Edit `src/main/resources/application.yml` to configure:
- Kafka broker settings
- Cassandra connection details
- Task scheduler settings
- Flink checkpointing configuration

## Architecture

![Architecture Diagram](docs/architecture.png)

1. **API Layer**: Handles HTTP requests and manages task lifecycle
2. **Scheduler Service**: Processes task scheduling using Flink
3. **Task Queue**: Kafka topics for task events and scheduling
4. **State Store**: RocksDB for local state management
5. **Persistence**: Cassandra for durable task storage

## Monitoring

The application exposes metrics via Spring Boot Actuator:
- `/actuator/health` - Application health check
- `/actuator/metrics` - Application metrics
- `/actuator/prometheus` - Prometheus metrics endpoint

## Scaling

To scale the application:
1. Run multiple instances of the application
2. Use Kafka consumer groups for parallel processing
3. Scale Cassandra cluster as needed

## License

MIT
