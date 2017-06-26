DROP PROCEDURE IF EXISTS drones.setServicesAndCategories;
DELIMITER #
CREATE PROCEDURE drones.setServicesAndCategories()

BEGIN

	DECLARE serviceCategoryId int unsigned default 0;

	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Film and TV (i.e. TV Commercial)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Events and Wedding (i.e. Corporate Event)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Real Estate (i.e. Building/home)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Asset Inspection', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Surveying', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Advertising and Marketing (i.e. Promotional Video)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Tourism (i.e. Resort/Hotel)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Surveying', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Construction (i.e. Progress Monitoring)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Asset Inspection', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Surveying', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Conservation and Forestry (i.e. Plant/Tree Health Analysis)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Asset Inspection', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Surveying', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Agriculture (i.e. Season Monitoring)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Asset Inspection', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Surveying', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Mining (i.e. Blast Profiling)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Asset Inspection', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Surveying', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Energy (i.e. Machinery Inspection)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Surveying', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Infrastructure (i.e. Mapping/Survey)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Asset Inspection', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Surveying', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Emergency Services (i.e. Search &amp; Rescue)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Surveying', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Media (i.e. News coverage)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Other', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Asset Inspection', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Surveying', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Insurance/Damage Claims (i.e. Roof Inspections)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Filming', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Aerial Photography', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Film Editing ONLY', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Film Editing ONLY', serviceCategoryId);

END #
DELIMITER ;

call drones.setServicesAndCategories();

INSERT INTO drones.PAID_OPTIONS(TITLE, DESCRIPTION, PRICE, CURRENCY) VALUES
	('Featured Project', 'I want my project to be listed as a featured project. Featured projects attract more, higher-quality bids and are displayed prominently on the \'Featured Jobs\' page.', 29.00, 'USD'),
	('Urgent Project', 'I want my project to be marked as an urgent project. Receive a faster response from drone pilots to get your project started within 24 - 48 hours!', 15.00, 'USD'),
	('Private Project', 'I want to hide project details from search engines and users that are not logged in. This feature is recommended for projects where confidentiality is a must.', 19.00, 'USD');

INSERT INTO drones.COUNTRIES (NAME) VALUES
	('Afghanistan', 0),
	('Albania', 0),
	('Algeria', 0),
	('American Samoa', 0),
	('Andorra', 0),
	('Angola', 0),
	('Anguilla', 0),
	('Antarctica', 0),
	('Antigua and Barbuda', 0),
	('Argentina', 0),
	('Armenia', 0),
	('Aruba', 0),
	('Australia', 1),
	('Austria', 0),
	('Azerbaijan', 0),
	('The Bahamas', 0),
	('Bahrain', 0),
	('Bangladesh', 0),
	('Barbados', 0),
	('Belarus', 0),
	('Belgium', 0),
	('Belize', 0),
	('Benin', 0),
	('Bermuda', 0),
	('Bhutan', 0),
	('Bolivia', 0),
	('Bosnia and Herzegovina', 0),
	('Botswana', 0),
	('Bouvet Island', 0),
	('Brazil', 0),
	('British Indian Ocean Territory', 0),
	('British Virgin Islands', 0),
	('Brunei Darussalam', 0),
	('Bulgaria', 0),
	('Burkina Faso', 0),
	('Burma', 0),
	('Burundi', 0),
	('Cambodia', 0),
	('Cameroon', 0),
	('Canada', 1),
	('Cape Verde', 0),
	('Cayman Islands', 0),
	('Central African Republic', 0),
	('Chad', 0),
	('Chile', 0),
	('China', 0),
	('Christmas Island', 0),
	('Cocos (Keeling) Islands', 0),
	('Colombia', 0),
	('Comoros', 0),
	('Congo, Democratic Republic of the', 0),
	('Congo, Republic of the', 0),
	('Cook Islands', 0),
	('Costa Rica', 0),
	('Cote d\'Ivoire', 0),
	('Croatia', 0),
	('Cuba', 0),
	('Cyprus', 0),
	('Czech Republic', 0),
	('Denmark', 0),
	('Djibouti', 0),
	('Dominica', 0),
	('Dominican Republic', 0),
	('East Timor', 0),
	('Ecuador', 0),
	('Egypt', 0),
	('El Salvador', 0),
	('Equatorial Guinea', 0),
	('Eritrea', 0),
	('Estonia', 0),
	('Ethiopia', 0),
	('Falkland Islands (Islas Malvinas)', 0),
	('Faroe Islands', 0),
	('Fiji', 0),
	('Finland', 0),
	('France', 1),
	('French Guiana', 0),
	('French Polynesia', 0),
	('French Southern and Antarctic Lands', 0),
	('Gabon', 0),
	('The Gambia', 0),
	('Georgia', 0),
	('Germany', 1),
	('Ghana', 0),
	('Gibraltar', 0),
	('Greece', 0),
	('Greenland', 0),
	('Grenada', 0),
	('Guadeloupe', 0),
	('Guam', 0),
	('Guatemala', 0),
	('Guinea', 0),
	('Guinea-Bissau', 0),
	('Guyana', 0),
	('Haiti', 0),
	('Heard Island and McDonald Islands', 0),
	('Holy See (Vatican City)', 0),
	('Honduras', 0),
	('Hong Kong (SAR)', 0),
	('Hungary', 0),
	('Iceland', 0),
	('India', 0),
	('Indonesia', 0),
	('Iran', 0),
	('Iraq', 0),
	('Ireland', 1),
	('Israel', 0),
	('Italy', 0),
	('Jamaica', 0),
	('Japan', 0),
	('Jordan', 0),
	('Kazakhstan', 0),
	('Kenya', 0),
	('Kiribati', 0),
	('Korea, North', 0),
	('Korea, South', 0),
	('Kuwait', 0),
	('Kyrgyzstan', 0),
	('Laos', 0),
	('Latvia', 0),
	('Lebanon', 0),
	('Lesotho', 0),
	('Liberia', 0),
	('Libya', 0),
	('Liechtenstein', 0),
	('Lithuania', 0),
	('Luxembourg', 0),
	('Macao', 0),
	('Macedonia, The Former Yugoslav Republic of', 0),
	('Madagascar', 0),
	('Malawi', 0),
	('Malaysia', 0),
	('Maldives', 0),
	('Mali', 0),
	('Malta', 0),
	('Marshall Islands', 0),
	('Martinique', 0),
	('Mauritania', 0),
	('Mauritius', 0),
	('Mayotte', 0),
	('Mexico', 1),
	('Micronesia, Federated States of', 0),
	('Moldova', 0),
	('Monaco', 0),
	('Mongolia', 0),
	('Montserrat', 0),
	('Morocco', 0),
	('Mozambique', 0),
	('Namibia', 0),
	('Nauru', 0),
	('Nepal', 0),
	('Netherlands', 0),
	('Netherlands Antilles', 0),
	('New Caledonia', 0),
	('New Zealand', 1),
	('Nicaragua', 0),
	('Niger', 0),
	('Nigeria', 0),
	('Niue', 0),
	('Norfolk Island', 0),
	('Northern Mariana Islands', 0),
	('Norway', 0),
	('Oman', 0),
	('Pakistan', 0),
	('Palau', 0),
	('Panama', 0),
	('Papua New Guinea', 0),
	('Paraguay', 0),
	('Peru', 0),
	('Philippines', 0),
	('Pitcairn Islands', 0),
	('Poland', 0),
	('Portugal', 0),
	('Puerto Rico', 0),
	('Qatar', 0),
	('Reunion', 0),
	('Romania', 0),
	('Russia', 0),
	('Rwanda', 0),
	('Saint Helena', 0),
	('Saint Kitts and Nevis', 0),
	('Saint Lucia', 0),
	('Saint Pierre and Miquelon', 0),
	('Saint Vincent and the Grenadines', 0),
	('Samoa', 0),
	('San Marino', 0),
	('Sao Tome and Principe', 0),
	('Saudi Arabia', 0),
	('Senegal', 0),
	('Seychelles', 0),
	('Sierra Leone', 0),
	('Singapore', 1),
	('Slovakia', 0),
	('Slovenia', 0),
	('Solomon Islands', 0),
	('Somalia', 0),
	('South Africa', 0),
	('South Georgia and the South Sandwich Islands', 0),
	('Spain', 0),
	('Sri Lanka', 0),
	('Sudan', 0),
	('Suriname', 0),
	('Svalbard', 0),
	('Swaziland', 0),
	('Sweden', 0),
	('Switzerland', 0),
	('Syria', 0),
	('Taiwan', 0),
	('Tajikistan', 0),
	('Tanzania', 0),
	('Thailand', 0),
	('Togo', 0),
	('Tokelau', 0),
	('Tonga', 0),
	('Trinidad and Tobago', 0),
	('Tunisia', 0),
	('Turkey', 0),
	('Turkmenistan', 0),
	('Turks and Caicos Islands', 0),
	('Tuvalu', 0),
	('Uganda', 0),
	('Ukraine', 0),
	('United Arab Emirates', 0),
	('United Kingdom', 1),
	('United States', 1),
	('United States Minor Outlying Islands', 0),
	('Uruguay', 0),
	('Uzbekistan', 0),
	('Vanuatu', 0),
	('Venezuela', 0),
	('Vietnam', 0),
	('Virgin Islands', 0),
	('Wallis and Futuna', 0),
	('Western Sahara', 0),
	('Yemen', 0),
	('Yugoslavia', 0),
	('Zambia', 0),
	('Zimbabwe', 0),
	('Palestinian Territory, Occupied', 0);

INSERT INTO drones.STATES (NAME, CODE) VALUES
	('Alabama', 'AL'),
	('Alaska', 'AK'),
	('Arizona', 'AZ'),
	('Arkansas', 'AR'),
	('California', 'CA'),
	('Colorado', 'CO'),
	('Connecticut', 'CT'),
	('Delaware', 'DE'),
	('District of Columbia', 'DC'),
	('Florida', 'FL'),
	('Georgia', 'GA'),
	('Hawaii', 'HI'),
	('Idaho', 'ID'),
	('Illinois', 'IL'),
	('Indiana', 'IN'),
	('Iowa', 'IA'),
	('Kansas', 'KS'),
	('Kentucky', 'KY'),
	('Louisiana', 'LA'),
	('Maine', 'ME'),
	('Maryland', 'MD'),
	('Massachusetts', 'MA'),
	('Michigan', 'MI'),
	('Minnesota', 'MN'),
	('Mississippi', 'MS'),
	('Missouri', 'MO'),
	('Montana', 'MT'),
	('Nebraska', 'NE'),
	('Nevada', 'NV'),
	('New Hampshire', 'NH'),
	('New Jersey', 'NJ'),
	('New Mexico', 'NM'),
	('New York', 'NY'),
	('North Carolina', 'NC'),
	('North Dakota', 'ND'),
	('Ohio', 'OH'),
	('Oklahoma', 'OK'),
	('Oregon', 'OR'),
	('Pennsylvania', 'PA'),
	('Rhode Island', 'RI'),
	('South Carolina', 'SC'),
	('South Dakota', 'SD'),
	('Tennessee', 'TN'),
	('Texas', 'TX'),
	('Utah', 'UT'),
	('Vermont', 'VT'),
	('Virginia', 'VA'),
	('Washington', 'WA'),
	('West Virginia', 'WV'),
	('Wisconsin', 'WI'),
	('Wyoming', 'WY');

INSERT INTO drones.GROUPS (NAME, ROLE) VALUES
	('General pilots group', 'ROLE_PILOT'),
	('General clients group', 'ROLE_CLIENT'),
	('General admins group', 'ROLE_ADMIN');
