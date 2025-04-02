# DopamineDB

DopamineDB is the most scalable Distributed Key-Value store ever developed. With advanced scalability and distributed architecture, DopamineDB is built to handle immense volumes of data across multiple nodes, making it ideal for modern applications that require fast, reliable, and scalable storage.

## Features

- **Ultra-Scalable**: DopamineDB can scale seamlessly across multiple nodes, ensuring high availability and reliability.
- **Distributed**: Leverages distributed architecture for fault tolerance and load balancing.
- **Fast Performance**: Optimized for low-latency and high-throughput, making it suitable for a wide range of workloads.
- **Platform Support**: Supports Linux/AMD64 and ARM64 architectures, making it versatile and adaptable to a variety of environments.

## Getting Started

### Prerequisites

- **Docker**: Ensure you have Docker installed on your machine. You can get it from [here](https://www.docker.com/get-started).

### Building DopamineDB

To build DopamineDB, use the following command:

```bash
docker buildx build --platform linux/amd64,linux/arm64 -t dopamine --load .
```


To Run this Docker file 

```bash 
docker run --platform linux/amd64 -p 8080:8080 dopamine .
```