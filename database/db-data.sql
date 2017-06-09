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
	('Afghanistan'),
	('Albania'),
	('Algeria'),
	('American Samoa'),
	('Andorra'),
	('Angola'),
	('Anguilla'),
	('Antarctica'),
	('Antigua and Barbuda'),
	('Argentina'),
	('Armenia'),
	('Aruba'),
	('Australia'),
	('Austria'),
	('Azerbaijan'),
	('The Bahamas'),
	('Bahrain'),
	('Bangladesh'),
	('Barbados'),
	('Belarus'),
	('Belgium'),
	('Belize'),
	('Benin'),
	('Bermuda'),
	('Bhutan'),
	('Bolivia'),
	('Bosnia and Herzegovina'),
	('Botswana'),
	('Bouvet Island'),
	('Brazil'),
	('British Indian Ocean Territory'),
	('British Virgin Islands'),
	('Brunei Darussalam'),
	('Bulgaria'),
	('Burkina Faso'),
	('Burma'),
	('Burundi'),
	('Cambodia'),
	('Cameroon'),
	('Canada'),
	('Cape Verde'),
	('Cayman Islands'),
	('Central African Republic'),
	('Chad'),
	('Chile'),
	('China'),
	('Christmas Island'),
	('Cocos (Keeling) Islands'),
	('Colombia'),
	('Comoros'),
	('Congo, Democratic Republic of the'),
	('Congo, Republic of the'),
	('Cook Islands'),
	('Costa Rica'),
	('Cote d\'Ivoire'),
	('Croatia'),
	('Cuba'),
	('Cyprus'),
	('Czech Republic'),
	('Denmark'),
	('Djibouti'),
	('Dominica'),
	('Dominican Republic'),
	('East Timor'),
	('Ecuador'),
	('Egypt'),
	('El Salvador'),
	('Equatorial Guinea'),
	('Eritrea'),
	('Estonia'),
	('Ethiopia'),
	('Falkland Islands (Islas Malvinas)'),
	('Faroe Islands'),
	('Fiji'),
	('Finland'),
	('France'),
	('French Guiana'),
	('French Polynesia'),
	('French Southern and Antarctic Lands'),
	('Gabon'),
	('The Gambia'),
	('Georgia'),
	('Germany'),
	('Ghana'),
	('Gibraltar'),
	('Greece'),
	('Greenland'),
	('Grenada'),
	('Guadeloupe'),
	('Guam'),
	('Guatemala'),
	('Guinea'),
	('Guinea-Bissau'),
	('Guyana'),
	('Haiti'),
	('Heard Island and McDonald Islands'),
	('Holy See (Vatican City)'),
	('Honduras'),
	('Hong Kong (SAR)'),
	('Hungary'),
	('Iceland'),
	('India'),
	('Indonesia'),
	('Iran'),
	('Iraq'),
	('Ireland'),
	('Israel'),
	('Italy'),
	('Jamaica'),
	('Japan'),
	('Jordan'),
	('Kazakhstan'),
	('Kenya'),
	('Kiribati'),
	('Korea, North'),
	('Korea, South'),
	('Kuwait'),
	('Kyrgyzstan'),
	('Laos'),
	('Latvia'),
	('Lebanon'),
	('Lesotho'),
	('Liberia'),
	('Libya'),
	('Liechtenstein'),
	('Lithuania'),
	('Luxembourg'),
	('Macao'),
	('Macedonia, The Former Yugoslav Republic of'),
	('Madagascar'),
	('Malawi'),
	('Malaysia'),
	('Maldives'),
	('Mali'),
	('Malta'),
	('Marshall Islands'),
	('Martinique'),
	('Mauritania'),
	('Mauritius'),
	('Mayotte'),
	('Mexico'),
	('Micronesia, Federated States of'),
	('Moldova'),
	('Monaco'),
	('Mongolia'),
	('Montserrat'),
	('Morocco'),
	('Mozambique'),
	('Namibia'),
	('Nauru'),
	('Nepal'),
	('Netherlands'),
	('Netherlands Antilles'),
	('New Caledonia'),
	('New Zealand'),
	('Nicaragua'),
	('Niger'),
	('Nigeria'),
	('Niue'),
	('Norfolk Island'),
	('Northern Mariana Islands'),
	('Norway'),
	('Oman'),
	('Pakistan'),
	('Palau'),
	('Panama'),
	('Papua New Guinea'),
	('Paraguay'),
	('Peru'),
	('Philippines'),
	('Pitcairn Islands'),
	('Poland'),
	('Portugal'),
	('Puerto Rico'),
	('Qatar'),
	('Reunion'),
	('Romania'),
	('Russia'),
	('Rwanda'),
	('Saint Helena'),
	('Saint Kitts and Nevis'),
	('Saint Lucia'),
	('Saint Pierre and Miquelon'),
	('Saint Vincent and the Grenadines'),
	('Samoa'),
	('San Marino'),
	('Sao Tome and Principe'),
	('Saudi Arabia'),
	('Senegal'),
	('Seychelles'),
	('Sierra Leone'),
	('Singapore'),
	('Slovakia'),
	('Slovenia'),
	('Solomon Islands'),
	('Somalia'),
	('South Africa'),
	('South Georgia and the South Sandwich Islands'),
	('Spain'),
	('Sri Lanka'),
	('Sudan'),
	('Suriname'),
	('Svalbard'),
	('Swaziland'),
	('Sweden'),
	('Switzerland'),
	('Syria'),
	('Taiwan'),
	('Tajikistan'),
	('Tanzania'),
	('Thailand'),
	('Togo'),
	('Tokelau'),
	('Tonga'),
	('Trinidad and Tobago'),
	('Tunisia'),
	('Turkey'),
	('Turkmenistan'),
	('Turks and Caicos Islands'),
	('Tuvalu'),
	('Uganda'),
	('Ukraine'),
	('United Arab Emirates'),
	('United Kingdom'),
	('United States'),
	('United States Minor Outlying Islands'),
	('Uruguay'),
	('Uzbekistan'),
	('Vanuatu'),
	('Venezuela'),
	('Vietnam'),
	('Virgin Islands'),
	('Wallis and Futuna'),
	('Western Sahara'),
	('Yemen'),
	('Yugoslavia'),
	('Zambia'),
	('Zimbabwe'),
	('Palestinian Territory, Occupied');

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
