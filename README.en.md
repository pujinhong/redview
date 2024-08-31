# Red-View
### A Multi-DataSource Data Query Service
## Introduction
I develop backend services for various dashboard systems.   
My frontend colleagues use GoView to quickly build these dashboards.   
I noticed that dashboard systems often require extensive statistical analysis and multi-data source query interfaces.   
Therefore,   
I designed a configurable backend service to meet the needs of GoView.

## Features
* Supports multiple databases, including PostgreSQL, MySQL, Oracle, SQL Server, ClickHouse, and JSON.
* Provides a configuration interface for setting up views and their data sources in a graphical manner.
* Lightweight – focuses solely on queries without unnecessary components, suitable for embedding into microservices architectures, currently supports Spring Cloud Alibaba.

## TODO
* Composite Views: Build new views using multiple existing views as data sources to satisfy complex multi-source join queries.
* Improve Fuzzy Search: Currently uses named parameters (i.e., :paramName) for pre-compiling SQL statements, but this approach is not very friendly for fuzzy search scenarios.
* Integrate Files and Documents: Provide markdown previews for documents.