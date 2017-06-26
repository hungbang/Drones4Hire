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
	('Afghanistan', 'AF', 0),
	('Albania', 'AL', 0),
	('Algeria', 'DZ', 0),
	('American Samoa', 'AS', 0),
	('Andorra', 'AD', 0),
	('Angola', 'AO', 0),
	('Anguilla', 'AI', 0),
	('Antarctica', 'AQ', 0),
	('Antigua and Barbuda', 'AG', 0),
	('Argentina', 'AR', 0),
	('Armenia', 'AM', 0),
	('Aruba', 'AW', 0),
	('Australia', 'AU', 1),
	('Austria', 'AT', 0),
	('Azerbaijan', 'AZ', 0),
	('Bahamas', 'BS', 0),
	('Bahrain', 'BH', 0),
	('Bangladesh', 'BD', 0),
	('Barbados', 'BB', 0),
	('Belarus', 'BY', 0),
	('Belgium', 'BE', 0),
	('Belize', 'BZ', 0),
	('Benin', 'BJ', 0),
	('Bermuda', 'BM', 0),
	('Bhutan', 'BT', 0),
	('Bolivia', 'BO', 0),
	('Bosnia and Herzegovina', 'BA', 0),
	('Botswana', 'BW', 0),
	('Bouvet Island', 'BV', 0),
	('Brazil', 'BR', 0),
	('British Indian Ocean Territory', 'IO', 0),
	('Brunei Darussalam', 'BN', 0),
	('Bulgaria', 'BG', 0),
	('Burkina Faso', 'BF', 0),
	('Burundi', 'BI', 0),
	('Cambodia', 'KH', 0),
	('Cameroon', 'CM', 0),
	('Canada', 'CA', 1),
	('Cape Verde', 'CV', 0),
	('Cayman Islands', 'KY', 0),
	('Central African Republic', 'CF', 0),
	('Chad', 'TD', 0),
	('Chile', 'CL', 0),
	('China', 'CN', 0),
	('Christmas Island', 'CX', 0),
	('Cocos (Keeling) Islands', 'CC', 0),
	('Colombia', 'CO', 0),
	('Comoros', 'KM', 0),
	('Congo', 'CG', 0),
	('Congo, the Democratic Republic of the', 'CD', 0),
	('Cook Islands', 'CK', 0),
	('Costa Rica', 'CR', 0),
	('Cote D''Ivoire', 'CI', 0),
	('Croatia', 'HR', 0),
	('Cuba', 'CU', 0),
	('Cyprus', 'CY', 0),
	('Czech Republic', 'CZ', 0),
	('Denmark', 'DK', 0),
	('Djibouti', 'DJ', 0),
	('Dominica', 'DM', 0),
	('Dominican Republic', 'DO', 0),
	('Ecuador', 'EC', 0),
	('Egypt', 'EG', 0),
	('El Salvador', 'SV', 0),
	('Equatorial Guinea', 'GQ', 0),
	('Eritrea', 'ER', 0),
	('Estonia', 'EE', 0),
	('Ethiopia', 'ET', 0),
	('Falkland Islands (Malvinas)', 'FK', 0),
	('Faroe Islands', 'FO', 0),
	('Fiji', 'FJ', 0),
	('Finland', 'FI', 0),
	('France', 'FR', 1),
	('French Guiana', 'GF', 0),
	('French Polynesia', 'PF', 0),
	('French Southern Territories', 'TF', 0),
	('Gabon', 'GA', 0),
	('Gambia', 'GM', 0),
	('Georgia', 'GE', 0),
	('Germany', 'DE', 1),
	('Ghana', 'GH', 0),
	('Gibraltar', 'GI', 0),
	('Greece', 'GR', 0),
	('Greenland', 'GL', 0),
	('Grenada', 'GD', 0),
	('Guadeloupe', 'GP', 0),
	('Guam', 'GU', 0),
	('Guatemala', 'GT', 0),
	('Guinea', 'GN', 0),
	('Guinea-Bissau', 'GW', 0),
	('Guyana', 'GY', 0),
	('Haiti', 'HT', 0),
	('Heard Island and Mcdonald Islands', 'HM', 0),
	('Holy See (Vatican City State)', 'VA', 0),
	('Honduras', 'HN', 0),
	('Hong Kong', 'HK', 0),
	('Hungary', 'HU', 0),
	('Iceland', 'IS', 0),
	('India', 'IN', 0),
	('Indonesia', 'ID', 0),
	('Iran, Islamic Republic of', 'IR', 0),
	('Iraq', 'IQ', 0),
	('Ireland', 'IE', 1),
	('Israel', 'IL', 0),
	('Italy', 'IT', 0),
	('Jamaica', 'JM', 0),
	('Japan', 'JP', 0),
	('Jordan', 'JO', 0),
	('Kazakhstan', 'KZ', 0),
	('Kenya', 'KE', 0),
	('Kiribati', 'KI', 0),
	('Korea, Democratic People''s Republic of', 'KP', 0),
	('Korea, Republic of', 'KR', 0),
	('Kuwait', 'KW', 0),
	('Kyrgyzstan', 'KG', 0),
	('Lao People''s Democratic Republic', 'LA', 0),
	('Latvia', 'LV', 0),
	('Lebanon', 'LB', 0),
	('Lesotho', 'LS', 0),
	('Liberia', 'LR', 0),
	('Libyan Arab Jamahiriya', 'LY', 0),
	('Liechtenstein', 'LI', 0),
	('Lithuania', 'LT', 0),
	('Luxembourg', 'LU', 0),
	('Macao', 'MO', 0),
	('Macedonia, the Former Yugoslav Republic of', 'MK', 0),
	('Madagascar', 'MG', 0),
	('Malawi', 'MW', 0),
	('Malaysia', 'MY', 0),
	('Maldives', 'MV', 0),
	('Mali', 'ML', 0),
	('Malta', 'MT', 0),
	('Marshall Islands', 'MH', 0),
	('Martinique', 'MQ', 0),
	('Mauritania', 'MR', 0),
	('Mauritius', 'MU', 0),
	('Mayotte', 'YT', 0),
	('Mexico', 'MX', 1),
	('Micronesia, Federated States of', 'FM', 0),
	('Moldova, Republic of', 'MD', 0),
	('Monaco', 'MC', 0),
	('Mongolia', 'MN', 0),
	('Montserrat', 'MS', 0),
	('Morocco', 'MA', 0),
	('Mozambique', 'MZ', 0),
	('Myanmar', 'MM', 0),
	('Namibia', 'NA', 0),
	('Nauru', 'NR', 0),
	('Nepal', 'NP', 0),
	('Netherlands', 'NL', 0),
	('Netherlands Antilles', 'AN', 0),
	('New Caledonia', 'NC', 0),
	('New Zealand', 'NZ', 1),
	('Nicaragua', 'NI', 0),
	('Niger', 'NE', 0),
	('Nigeria', 'NG', 0),
	('Niue', 'NU', 0),
	('Norfolk Island', 'NF', 0),
	('Northern Mariana Islands', 'MP', 0),
	('Norway', 'NO', 0),
	('Oman', 'OM', 0),
	('Pakistan', 'PK', 0),
	('Palau', 'PW', 0),
	('Palestinian Territory, Occupied', 'PS', 0),
	('Panama', 'PA', 0),
	('Papua New Guinea', 'PG', 0),
	('Paraguay', 'PY', 0),
	('Peru', 'PE', 0),
	('Philippines', 'PH', 0),
	('Pitcairn', 'PN', 0),
	('Poland', 'PL', 0),
	('Portugal', 'PT', 0),
	('Puerto Rico', 'PR', 0),
	('Qatar', 'QA', 0),
	('Reunion', 'RE', 0),
	('Romania', 'RO', 0),
	('Russian Federation', 'RU', 0),
	('Rwanda', 'RW', 0),
	('Saint Helena', 'SH', 0),
	('Saint Kitts and Nevis', 'KN', 0),
	('Saint Lucia', 'LC', 0),
	('Saint Pierre and Miquelon', 'PM', 0),
	('Saint Vincent and the Grenadines', 'VC', 0),
	('Samoa', 'WS', 0),
	('San Marino', 'SM', 0),
	('Sao Tome and Principe', 'ST', 0),
	('Saudi Arabia', 'SA', 0),
	('Senegal', 'SN', 0),
	('Serbia and Montenegro', 'CS', 0),
	('Seychelles', 'SC', 0),
	('Sierra Leone', 'SL', 0),
	('Singapore', 'SG', 1),
	('Slovakia', 'SK', 0),
	('Slovenia', 'SI', 0),
	('Solomon Islands', 'SB', 0),
	('Somalia', 'SO', 0),
	('South Africa', 'ZA', 0),
	('South Georgia and the South Sandwich Islands', 'GS', 0),
	('Spain', 'ES', 0),
	('Sri Lanka', 'LK', 0),
	('Sudan', 'SD', 0),
	('Suriname', 'SR', 0),
	('Svalbard and Jan Mayen', 'SJ', 0),
	('Swaziland', 'SZ', 0),
	('Sweden', 'SE', 0),
	('Switzerland', 'CH', 0),
	('Syrian Arab Republic', 'SY', 0),
	('Taiwan, Province of China', 'TW', 0),
	('Tajikistan', 'TJ', 0),
	('Tanzania, United Republic of', 'TZ', 0),
	('Thailand', 'TH', 0),
	('Togo', 'TG', 0),
	('Tokelau', 'TK', 0),
	('Tonga', 'TO', 0),
	('Trinidad and Tobago', 'TT', 0),
	('Tunisia', 'TN', 0),
	('Turkey', 'TR', 0),
	('Turkmenistan', 'TM', 0),
	('Turks and Caicos Islands', 'TC', 0),
	('Tuvalu', 'TV', 0),
	('Uganda', 'UG', 0),
	('Ukraine', 'UA', 0),
	('United Arab Emirates', 'AE', 0),
	('United Kingdom', 'GB', 1),
	('United States', 'US', 1),
	('United States Minor Outlying Islands', 'UM', 0),
	('Uruguay', 'UY', 0),
	('Uzbekistan', 'UZ', 0),
	('Vanuatu', 'VU', 0),
	('Venezuela', 'VE', 0),
	('Viet Nam', 'VN', 0),
	('Virgin Islands, British', 'VG', 0),
	('Virgin Islands, U.s.', 'VI', 0),
	('Wallis and Futuna', 'WF', 0),
	('Western Sahara', 'EH', 0),
	('Yemen', 'YE', 0),
	('Zambia', 'ZM', 0),
	('Zimbabwe', 'ZW', 0);

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
