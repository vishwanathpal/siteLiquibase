package com.site.manager;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import liquibase.integration.spring.SpringLiquibase;

@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class MasterLiquibaseConfiguration {

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	public SpringLiquibase liquibase(DataSource dataSource, LiquibaseProperties properties)
			throws IOException, ClassNotFoundException, SQLException {

		SpringLiquibase liquibase = null;

		createDatabase();

		// iModuleDatabaseExportImportService.exportFromMainEnvironmentAndImportToPreEnvironment();
		liquibase = new SpringLiquibase();

		liquibase.setChangeLog(properties.getChangeLog());
		liquibase.setContexts(properties.getContexts());
		liquibase.setDataSource(dataSource);
		liquibase.setDefaultSchema(properties.getDefaultSchema());
		liquibase.setDropFirst(properties.isDropFirst());
		liquibase.setShouldRun(properties.isEnabled());
		liquibase.setLabels(properties.getLabels());
		liquibase.setChangeLogParameters(properties.getParameters());
		liquibase.setRollbackFile(properties.getRollbackFile());

		if (properties.getRollbackFile() != null) {
			File file = new File(properties.getRollbackFile().getAbsolutePath());
			String folderName = file.getParent();
			File folders = new File(folderName);
			folders.mkdirs();
		}
		return liquibase;

	}

	private void createDatabase() throws SQLException {

		// if(corePropertiesProvider.isDevelopment()) {
		String schemaName = null;
		String jdbcConnString = url;
		if (jdbcConnString.contains("?")) {
			schemaName = jdbcConnString.substring(jdbcConnString.lastIndexOf('/') + 1, jdbcConnString.indexOf('?'));
		} else {
			schemaName = jdbcConnString.substring(jdbcConnString.lastIndexOf('/') + 1);
		}
		String tempUrl = CoreStringUtil.omitLastPropertyFromDelimitedProperties(url, "/");

		logger.debug("destination database name {}", schemaName);
		logger.debug("temp url {}", tempUrl);
		try (Connection connection = DriverManager.getConnection(tempUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + schemaName);
			
		}
		// }
	}
}
