# SpringBootMultipleDataSource
SpringBoot application that support two Postgres datasource

* use application.properties file to configure data sources.
* use DataSourceConfig bean to initialize both datasources.
* each datasource has it's own entitymanager and transaction manager.
* API demostrats usage of each datasource.
* demo contain situations as below:
** table_one only belongs to datasource one.
** table_two only belongs to datasource two.
** table_three belongs to datasource one and two. and can be operated individually.
