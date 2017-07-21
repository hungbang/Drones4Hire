DROP PROCEDURE IF EXISTS drones.setServicesAndCategories;
DELIMITER #
CREATE PROCEDURE drones.setServicesAndCategories()

BEGIN

	DECLARE serviceCategoryId int unsigned default 0;
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Aerial Filming', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Advertising and Marketing (i.e. Promotional Video)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Agriculture (i.e. Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Conservation and Forestry (i.e. Plant/Tree Health Analysis)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Construction (i.e. Progress Monitoring)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Emergency Services (i.e. Search & Rescue)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Events and Wedding (i.e. Corporate Event)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Film and TV (i.e. TV Commercial)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Infrastructure (i.e. Mapping/Survey/Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Insurance/Damage Claims (i.e. Roof Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Media (i.e. News coverage/Live Broadcast)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Mining (i.e. Blast Profiling)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Oil/Gas/Solar Energy (i.e. Asset Inspections, Environmental Compliance)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Real Estate (i.e. Building/home)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Telecom (i.e. Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Tourism (i.e. Resort/Hotel)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Aerial Photography', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Advertising and Marketing (i.e. Promotional Video)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Agriculture (i.e. Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Conservation and Forestry (i.e. Plant/Tree Health Analysis)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Construction (i.e. Progress Monitoring)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Emergency Services (i.e. Search & Rescue)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Events and Wedding (i.e. Corporate Event)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Film and TV (i.e. TV Commercial)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Infrastructure (i.e. Mapping/Survey/Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Insurance/Damage Claims (i.e. Roof Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Media (i.e. News coverage/Live Broadcast)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Mining (i.e. Blast Profiling)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Oil/Gas/Solar Energy (i.e. Asset Inspections, Environmental Compliance)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Real Estate (i.e. Building/home)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Telecom (i.e. Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Tourism (i.e. Resort/Hotel)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Analysis', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Agriculture (i.e. Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Conservation and Forestry (i.e. Plant/Tree Health Analysis)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Construction (i.e. Progress Monitoring)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Infrastructure (i.e. Mapping/Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Insurance/Damage Claims (i.e. Roof Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Mining (i.e. Blast Profiling)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Oil/Gas/Solar Energy (i.e. Asset Inspections, Environmental Compliance)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Real Estate (i.e. Building/home)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Telecom (i.e. Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Underwater (i.e. Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Data Collection', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Agriculture (i.e. Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Conservation and Forestry (i.e. Plant/Tree Health Analysis)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Construction (i.e. Progress Monitoring)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Drone Fishing', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Infrastructure (i.e. Mapping/Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Insurance/Damage Claims (i.e. Roof Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Mining (i.e. Blast Profiling)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Oil/Gas/Solar Energy (i.e. Asset Inspections, Environmental Compliance)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Real Estate (i.e. Building/home)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Telecom (i.e. Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Underwater (i.e. Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Fishing Assistant', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Conservation and Forestry (i.e. Plant/Tree Health Analysis)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Drone Fishing', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Underwater (i.e. Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Inspection', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Infrastructure (i.e. Mapping/Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Insurance/Damage Claims (i.e. Roof Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Mining (i.e. Blast Profiling)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Oil/Gas/Solar Energy (i.e. Asset Inspections, Environmental Compliance)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Real Estate (i.e. Building/home)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Telecom (i.e. Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Underwater (i.e. Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Virtual 3D Walkthroughs (i.e. Floor Plans)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Mapping', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Agriculture (i.e. Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Conservation and Forestry (i.e. Plant/Tree Health Analysis)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Construction (i.e. Progress Monitoring)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Infrastructure (i.e. Mapping/Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Mining (i.e. Blast Profiling)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Oil/Gas/Solar Energy (i.e. Asset Inspections, Environmental Compliance)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Real Estate (i.e. Building/home)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Other (See Project Description)', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);

	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Photo/Video Editing', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Film Editing ONLY', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Virtual 3D Walkthroughs (i.e. Floor Plans)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Progress Monitoring', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Agriculture (i.e. Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Conservation and Forestry (i.e. Plant/Tree Health Analysis)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Construction (i.e. Progress Monitoring)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Infrastructure (i.e. Mapping/Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Mining (i.e. Blast Profiling)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Oil/Gas/Solar Energy (i.e. Asset Inspections, Environmental Compliance)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Virtual 3D Walkthroughs (i.e. Floor Plans)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Raw Footage', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Advertising and Marketing (i.e. Promotional Video)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Agriculture (i.e. Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Conservation and Forestry (i.e. Plant/Tree Health Analysis)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Construction (i.e. Progress Monitoring)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Emergency Services (i.e. Search & Rescue)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Events and Wedding (i.e. Corporate Event)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Film and TV (i.e. TV Commercial)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Infrastructure (i.e. Mapping/Survey/Data Collection)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Insurance/Damage Claims (i.e. Roof Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Media (i.e. News coverage/Live Broadcast)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Mining (i.e. Blast Profiling)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Oil/Gas/Solar Energy (i.e. Asset Inspections, Environmental Compliance)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Real Estate (i.e. Building/home)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Telecom (i.e. Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Tourism (i.e. Resort/Hotel)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Underwater (i.e. Inspections)', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Virtual 3D Walkthroughs (i.e. Floor Plans)', serviceCategoryId);
	INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Repair', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Drone Repair Services', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Search and Rescue', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Emergency Services (i.e. Search & Rescue)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Media (i.e. News coverage/Live Broadcast)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Underwater (i.e. Inspections)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Training', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Training Services', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
	
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Virtual Walkthroughs', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Virtual 3D Walkthroughs (i.e. Floor Plans)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
	INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Interior/Exterior Footage', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Advertising and Marketing (i.e. Promotional Video)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Events and Wedding (i.e. Corporate Event)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Real Estate (i.e. Building/home)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Tourism (i.e. Resort/Hotel)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Virtual 3D Walkthroughs (i.e. Floor Plans)', serviceCategoryId);
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
    
    INSERT INTO drones.SERVICE_CATEGORIES(NAME, SORT_ORDER) VALUES ('Other', 0);
    SET serviceCategoryId = LAST_INSERT_ID();
    INSERT INTO drones.SERVICES(NAME, SERVICE_CATEGORY_ID) VALUES ('Other', serviceCategoryId);
END #
DELIMITER ;

call drones.setServicesAndCategories();

INSERT INTO drones.PAID_OPTIONS(TITLE, DESCRIPTION, PRICE, CURRENCY) VALUES
	('Featured Project', 'I want my project to be listed as a featured project. Featured projects attract more, higher-quality bids and are displayed prominently on the \'Featured Jobs\' page.', 29.00, 'USD'),
	('Urgent Project', 'I want my project to be marked as an urgent project. Receive a faster response from drone pilots to get your project started within 24 - 48 hours!', 15.00, 'USD'),
	('Private Project', 'I want to hide project details from search engines and users that are not logged in. This feature is recommended for projects where confidentiality is a must.', 19.00, 'USD');

INSERT INTO drones.COUNTRIES (NAME, CODE, LICENSE_REQUIRED) VALUES
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

/*INSERT INTO drones.BUDGETS (TITLE, MIN, MAX, CURRENCY, SORT_ORDER) VALUES
	('Mini', 100, 350, 'USD', 0),
	('Small', 350, 1000, 'USD', 0),
	('Average', 1000, 3000, 'USD', 0),
	('Large', 3000, 5000, 'USD', 0),
	('Major', 5000, 0, 'USD', 0);*/
    
INSERT INTO drones.BUDGETS (TITLE, MIN, MAX, CURRENCY, SORT_ORDER) VALUES
	('$300 - $1000', 300, 1000, 'USD', 0),
	('$1001 - $2500', 1001, 2500, 'USD', 1),
	('$2501 - $5000', 2501, 5000, 'USD', 2),
	('$5001 - $10000', 5001, 10000, 'USD', 3);
	('$10001 +', 10001, 0, 'USD', 4);
    
INSERT INTO drones.DURATIONS (TITLE, MIN, MAX, SORT_ORDER) VALUES
	('1-2', 1, 2, 0),
	('3-4', 3, 4, 0),
	('5-8', 5, 8, 0),
	('9+', 9, 0, 0);
    
INSERT INTO drones.FAQS (QUESTION, ANSWER, LOCALE, SORT_ORDER) VALUES
	('What is Drones4Hire?', '<p>Dones4Hire utilizes innovative technology plus our aviation expertise to take the hassle out of hiring drone pilots.</p>', 'en_US', 0),
	('Who founded Drones4Hire?', '<p>Drones4Hire CEO Cory Mullins is a FAA-certified remote pilot with a Masters&#8217; in aviation from the University of North Dakota. Cory has created college uav/drone programs and worked in management roles for major airlines including Delta, Southwest, and American. Read more about Cory and our company <a href="https://drones.qaprosoft.com/#/about-us">here</a>.<br/></p>', 'en_US', 1),
	('When will Drones4Hire be fully operational?', '<p>We are currently accepting pilot registrations; we&#8217;ll start accepting client registrations and job postings during October of 2016.<br/></p>', 'en_US', 2),
	('Who should apply as a Drones4Hire pilot?', '<p>We are currently accepting registrations from drone operators, drone pilots, camera and production companies, drone businesses, and providers of all aerial drone services (including video, photography, thermal imaging, 3D mapping, surveying and inspections).<br/></p>', 'en_US', 3),
	('What type of jobs can I list on Drones4Hire?', '<p>We accept job listings for all types of drone jobs including film &amp; TV, events, weddings, real estate, advertising &amp; marketing, news &amp; media coverage, insurance &amp; damage claims, tourism, construction, agriculture, conservation &amp; forestry, mining, energy, infrastructure, and emergency services, among others.<br/></p>', 'en_US', 4),
	('Does Drones4hire charge a fee?', '<p>It costs nothing to create a profile and post projects on our platform. Drones4Hire retains a small percentage of every completed project made through our platform.<br/></p>', 'en_US', 5),
	('What if I have a question or problem?', '<p>Drones4hire offers quick, responsive customer service by email support@drones4hire.com, or by phone at 1-877-577-1782. We work with clients and drone pilots to ensure your satisfaction.<br/></p>', 'en_US', 6);
    
INSERT INTO drones.TERMS (TEXT, LOCALE) VALUES
	('<p>THIS DOCUMENT CONTAINS VERY IMPORTANT INFORMATION REGARDING YOUR RIGHTS AND OBLIGATIONS, AS WELL AS CONDITIONS, LIMITATIONS AND EXCLUSIONS THAT MIGHT APPLY TO YOU. PLEASE READ IT CAREFULLY. THESE TERMS REQUIRE THE USE OF ARBITRATION ON AN INDIVIDUAL BASIS TO RESOLVE DISPUTES, RATHER THAN JURY TRIALS OR CLASS ACTIONS. BY USING THIS WEBSITE OR CONTRACTING FOR SERVICES WITH A THIRD PARTY(&#8220;PROVIDER&#8221;) FROM THIS WEBSITE, YOU ACCEPT AND ARE BOUND BY THESE TERMS AND CONDITIONS.</p><p>YOU MAY NOT ORDER OR OBTAIN SERVICES FROM THIS WEBSITE IF YOU (A) DO NOT AGREE TO THESE TERMS, (B) ARE NOT THE OLDER OF (i) AT LEAST 18 YEARS OF AGE OR (ii) LEGAL AGE TO FORM A BINDING CONTRACT OR (C) ARE PROHIBITED FROM ACCESSING OR USING THIS WEBSITE OR ANY OF THIS WEBSITE&#8217;S CONTENTS OR SERVICES BY APPLICABLE LAW.</p><p>These terms and conditions (these &#8220;Terms&#8221;) apply to the purchase of any services through (the &#8220;Site&#8221;). These Terms are subject to change by Drones 4 Hire, LLC (referred to as &#8220;drones 4 hire&#8221;,&#8220;us&#8221;, &#8220;we&#8221;, or &#8220;our&#8221; as the context may require) without prior written notice at any time, in our sole discretion. The latest version of these Terms will be posted on this Site, and you should review these Terms prior to use of any services on this Site. Your continued use of this Site after a posted change in these Terms will constitute your acceptance of and agreement to such changes.</p><p>These Terms are an integral part of the Website Terms of Use that apply generally to the use of our Site. You should also carefully review our Privacy Policy before placing an order for services through this Site.</p><p>Description of Service. Drones 4 Hire, LLC provides User with a website that is an online venue for buyers (referred to hereinafter as &#8220;Buyer&#8221; or &#8220;Buyers&#8221; as the context may require) and service providers (referred to as &#8220;Provider&#8221;, &#8220;Providers&#8221; or &#8220;Operator&#8221; as the context may require)to connect and contract with one another for various services using an unmanned aerial vehicle (UAV) or &#8220;drone&#8221;. Buyers and Providers must register for an Account in order to bid, post projects, and buy or sell services and products. Drones 4 Hire, LLC is not a party to any contract or agreement entered into by Buyers and Providers. Drones 4 Hire, LLC does not warrant the quality of any services provided by Providers nor that the services will be suitable for Buyer&#8217;s needs. Drones 4 Hire, LLC depends upon the Buyers and Providers to warrant that they will follow the terms of any agreement or contract made between the two parties.</p><p>Definitions:</p><p><strong>&#34;Account&#34;</strong>&#160;means the account you open when you register on the Website.</p><p><strong>&#34;Buyer means&#34;</strong>&#160;a User that investigates and purchases Provider Services or items from Provider or identifies a Provider through the Website.</p><p><strong>&#34;Inactive Account&#34;</strong>&#160;means a User Account that has not been logged into for a continuous 6 month period.</p><p><strong>&#34;Payment&#34;</strong>&#160;means a prepayment made to Drones 4 Hire, LLC by the Buyer for the provision of Provider Services under a User Contract and which will be released in accordance with the section &#34;Create Payments&#34; below.</p><p><strong>&#34;Project&#34; or &#34;Job&#34;</strong>&#160;means a job offered or awarded by a Buyer via the Website, which may include a Project by a Buyer, a project awarded by a Buyer (for example through &#8220;Award&#8221; project), a service bought by a Buyer from a Provider, and service awarded by a Buyer to a Provider as a result of competitive bidding hosted via the Website.</p><p><strong>&#34;User&#34;, &#34;you&#34; or &#34;your&#34;</strong>&#160;means an individual who visits or uses the website</p><p><strong>&#34;Website&#34;</strong>&#160;collectively means the websites operated by Drones 4 Hire, LLC and available at&#160;<a href="http://www.drones4hire.com/">www.drones4hire.com</a><span>&#160;</span>and&#160;any related Drones 4 Hire, LLC&#8217;s service, tool or application.</p><p><strong>Prices and Payment Terms.</strong></p><p><strong>(a) Fees :&#160;</strong>Submitting/posting a listing to Drones 4 Hire Service is free. If a Provider is subsequently hired to perform a Service then a fee (hereinafter &#8220;Commission&#8221; or &#8220;Commissions&#8221; as the context may require) of 20% of the total service price is charged. This is charged when the service is ordered by way of a deduction from the payment to Provider. When Buyers and Providers enter into a contract for services, Drones 4 Hire, LLC collects a commission (&#8220;Commission Rates&#8221;) on the purchase price for its services as the parties&#8217; agent for payment. Commissions are only charged to Providers, at the time the funds for a service are released by the Buyer and to the Provider. All Commission rates posted on this Site are subject to change without notice. The Commission rate charged for services contracted with the third party Provider, will be the price in effect at the time the project is/was awarded and will be set out in the Provider&#8217;s invoice/receipt confirmation e-mail. Commission rate increases will only apply to orders placed after such changes.</p><p><strong>(b) Making a Payment :&#160;</strong>Terms of payment for Commission rates are within our sole discretion and unless otherwise agreed by us in writing, payment must be received by us before the Provider may perform the services. We accept PayPal, Visa, MasterCard, AMEX and Discover for all purchases. You represent and warrant that (i) the credit card or payment information you supply to us is true, correct and complete, (ii) you are duly authorized to use such method for the purchase, (iii) charges incurred by you will be honored by your credit card company or financial institution, and (iv) you will pay charges incurred by you at the posted prices, including all applicable taxes, if any.</p><p><strong>(c) Releasing Payment :&#160;</strong>When a Provider has provided the goods or services or completed the task requested by a Buyer seeking service , the Buyer must log on to the Website and click the &#34;Release &#34; button for that service/project/job. Failure to complete the service or task will constitute a breach of this User Agreement.</p><p><strong>(d) Insufficient funds :&#160;</strong>If any payment made by User fails or User&#8217;s account balance becomes negative for any reason, Drones 4 Hire, LLC reserves the right to take all actions permitted under the law to recover payment, including but not limited to set-off and payment reversal.</p><p><strong>(e) Account Balances :&#160;</strong>User may have a positive account balance from prepaid funds or released payments. Drones 4 Hire, LLC maintains all funds at reputable U.S. financial institutions. Drones 4 Hire does not provide any banking or escrow services. User&#8217;s funds will be commingled with those of other Users. User is not entitled to interest for any funds maintained in the account.</p><p><strong>(f) Withdrawing Payment :&#160;</strong>Funds will be made available for withdrawal from User&#8217;s account in accordance with Drones 4 Hire, LLC&#8217;s fraud prevention policy. The first withdrawal from a User&#8217;s account will be delayed by up to 15 days for verification purposes. Subsequent withdrawals may be subject to delay if necessary to comply with our fraud prevention policy. Drones 4 Hire, LLC reserves the right to delay any withdrawal when fraud is suspected.</p><p><strong>(g) Identity Verification :&#160;</strong>User agrees to submit all information and evidence requested by Drones 4 Hire, LLC to establish and verify identity. However, Drones 4 Hire, LLC does not guarantee the identity of any User or ensure that a Provider will complete a transaction.</p><p><strong>(h) Chargebacks :&#160;</strong>In the event of a chargeback, Drones 4 Hire, LLC is entitled to recover all losses and fees incurred and will use all legal means necessary to recover such funds. Any dispute involving a chargeback is strictly between the Buyer and Provider.</p><p>Limitation of Liability. Any services you purchase through the website are contracted with a third party Provider you select. Drones 4 Hire, LLC is not a party to your contract with the third party Provider. Drones 4 Hire, LLC only acts as an independent agent authorized only to collect payment from the Buyer on behalf of the Provider. Drones 4 Hire, LLC makes no claims or promises with respect to any third party, including Providers listed on the site. Accordingly, your purchase and use of products or services offered by Providers through the site is at your own discretion and risk. Any dispute between the Buyer and Provider must be resolved by those parties, Drone 4 Hire, LLC will not act as an arbiter or mediator.</p><p>DRONES 4 HIRE, LLC&#8217;S LIABILITY WILL UNDER NO CIRCUMSTANCES EXCEED THE ACTUAL COMMISISON RATE PAID BY BUYER FOR THE SERVICE THAT BUYER HAS PURCHASED THROUGH THE SITE, NOR WILL WE UNDER ANY CIRCUMSTANCES BE LIABLE FOR ANY LOSS OF PRODUCTION, WORK, DATA, USE, BUSINESS, GOODWILL, REPUTATION, REVENUE OR PROFIT, ANY DIMINUTION IN VALUE, COSTS OF REPLACEMENT GOODS OR SERVICES, OR ANY CONSEQUENTIAL, INCIDENTAL, SPECIAL OR PUNITIVE DAMAGES OR LOSSES, WHETHER DIRECT OR INDIRECT.</p><p><a href="https://www.drones4hire.com/preview/">WWW.DRONES4HIRE.COM</a>&#160;AND DRONES 4 HIRE, LLC WILL NOT BE LIABLE FOR ANY DAMAGES WHATSOEVER, AND IN PARTICULAR&#160;<a href="https://www.drones4hire.com/preview/">WWW.DRONES4HRE.COM</a>&#160;AND DRONES 4 HIRE, LLC WILL NOT BE LIABLE FOR ANY SPECIAL, INDIRECT, CONSEQUENTIAL, OR INCIDENTAL DAMAGES, OR DAMAGES FOR LOST PROFITS, LOSS OF REVENUE, OR LOSS OF USE, ARISING OUT OF OR RELATED TO THIS WEB SITE OR THE INFORMATION CONTAINED IN IT, WHETHER SUCH DAMAGES ARISE IN CONTRACT, NEGLIGENCE, TORT, UNDER STATUTE, IN EQUITY, AT LAW, OR OTHERWISE. DRONES 4 HIRE, LLC MAKES NO REPRESENTATIONS OR WARRANTIES OF ANY KIND, EXPRESS OR IMPLIED, ABOUT THE COMPLETENESS, ACCURACY, RELIABILITY, SUITABILITY OR AVAILABILITY WITH RESPECT TO THE WEBSITE OR THE INFORMATION, SERVICES, OR RELATED GRAPHICS, OR VIDEOS CONTAINED ON THE WEBSITE FOR ANY PURPOSE. ANY RELIANCE USER PLACES ON SUCH INFORMATION IS THEREFORE STRICTLY AT USER&#8217;S OWN RISK.</p><p>IN NO EVENT WILL WWW.DRONES4HIRE.COM OR DRONES 4 HIRE, LLC BE LIABLE FOR ANY LOSS OR DAMAGE INCLUDING WITHOUT LIMITATION, INDIRECT OR CONSEQUENTIAL LOSS OR DAMAGE, OR ANY LOSS OR DAMAGE WHATSOEVER ARISING FROM LOSS OF DATA OR PROFITS ARISING OUT OF OR IN CONNECTION WITH THE USE OF THIS WEBSITE.</p><p>USER ACKNOWLEDGES THAT&#160;<a href="https://www.drones4hire.com/preview/">WWW.DRONES4HIRE.COM</a>&#160;MAY CONTAIN LINKS TO OTHER WEBSITES WHICH ARE NOT UNDER THE CONTROL OF DRONES 4 HIRE, LLC AND DRONES 4 HIRE, LLC HAS NO CONTROL OVER THE NATURE AND CONTENT OF THOSE SITES. THE INCLUSION OF ANY LINKS DOES NOT IMPLY A RECOMMENDATION OR ENDORSEMENT OF ANY VIEWS EXPRESSED WITHIN THEM.</p><p>SOME STATES DO NOT ALLOW THE EXCLUSION OR LIMITATION OF INCIDENTAL OR CONSEQUENTIAL DAMAGES, SO THE ABOVE LIMITATION OR EXCLUSION MAY NOT APPLY TO YOU.</p><p>Personal Information. Listings for projects/jobs will provide the location where the services will be performed. For safety and liability reasons, a Buyer must never disclose in any project description or any other public communication on the Drones 4 Hire website, personal information such as the Buyer\'s name, street number, phone number or the email address.<span>&#160;</span><a href="https://www.drones4hire.com/preview/">www.drones4hire.com</a>&#160;has the right but not the responsibility to remove any listings containing such information. Drones 4 Hire, LLC bears no liability for any information Buyer shares in violation of this policy.</p><p>Completion of Work. Once a Provider&#8217;s bid has been accepted/awarded/hire/hired by a Buyer seeking projects/jobs, then the Provider providing the service or task is obliged to complete the transaction, unless the service or task, goods or transaction is prohibited by law, by this Agreement or by any of Drones 4 Hire, LLC&#8217;s policies. Likewise, once the Provider has fully completed the project/job, the Buyer is obliged to release payment to the Provider.</p><p>Revision of Project Post. If a Buyer updates a project after it has been published on the Website, then all bids for that project made prior to the update will be automatically cancelled and the bidding process will restart.</p><p>Modifications and Interruption to Site. Drones 4 Hire, LLC reserves the right to modify or discontinue the Site with or without notice to the User. Drones 4 Hire, LLC will not be liable to User or any third party should Drones 4 Hire, LLC choose to exercise its right to modify or discontinue the Site. User acknowledges and accepts that Drones 4 Hire, LLC does not guarantee continuous, uninterrupted or secure access to its website and operation of its website may be interfered with or adversely affected by numerous factors or circumstances outside of its control. User acknowledges and accepts that Drones 4 Hire, LLC does interrupt access briefly on a regular basis to perform site maintenance on operations.</p><p>Third-Party Sites.<span>&#160;</span><a href="https://www.drones4hire.com/preview/">www.drones4hire.com</a>&#160;may include links to other sites on the Internet that are owned and operated by third parties. User acknowledges that Drones 4 Hire, LLC is not responsible for the availability of, or the content located on or through, any third-party site. User should contact the site administrator or webmaster for those third-party sites if User has any concerns regarding such links or the content located on such sites. User&#8217;s use of those third-party sites is subject to the terms of use and privacy policies of each site, and Drones 4 Hire, LLC is not responsible therein. Drones 4 Hire, LLC encourages all Users to review said privacy policies of third-parties&#8217; sites.</p><p>Compliance with Laws. User assumes all knowledge of applicable law and is responsible for compliance with any such laws. User may not use the Service in any way that violates applicable state, federal, or international laws, regulations or other government requirements. User further agrees not to transmit any material that encourages conduct that could constitute a criminal offense, give rise to civil liability or otherwise violate any applicable local, state, national, or international law or regulation.</p><p>Content Upload Restrictions. User content uploaded to the website is published on a time delay and is not available for immediate viewing. All uploaded content is subject to review for appropriateness and consistency with the commercial purposes of the website. Drones 4 Hire, LLC is not required to, but may at its discretion, monitor any content uploaded to the website and expressly disclaims any and all liability in connection with uploaded content. Further, Drones 4 Hire, LLC, may remove ANY content deemed in its sole discretion to be unsuitable to the purpose of the website. You agree, through your use of www.drones4hire.com and related services offered by Drones 4 Hire, LLC, that you will not post any material which is false, libelous, defamatory, inaccurate, abusive, vulgar, hate-inciting, harassing, obscene, profane, pornographic, sexually oriented, threatening, invasive of a person\'s privacy, or otherwise illegal under any ordinance, regulation or statute. Legal action can be taken against you. You also agree not to post or upload any copyrighted material unless the copyright is owned by you. Spam, flooding, advertisements, chain letters, pyramid schemes, and solicitations are not permitted on this website.</p><p>User is solely responsible for all content and material uploaded to the website. By uploading content and material to&#160;<a href="https://www.drones4hire.com/preview/">www.drones4hire.com,</a>&#160;User affirms, represents, and warrants that User has all rights, consents, and licenses necessary to upload such submissions. Unless otherwise agreed with us, you must not advertise an external website, product or service on the Website.</p><p>Drones 4 Hire, LLC may publicly display advertisements and other information adjacent to or included with User content. You are not entitled to any compensation for such advertisements. The manner, mode and extent of such advertising are subject to change without specific notice to you. User may not modify, reproduce, distribute, create derivative works or adaptations of, publicly display third party content in any way. Furthermore, you acknowledge and agree that content of third party advertisements or promotions is protected by copyrights, trademarks, service marks, patents or other intellectual property or proprietary rights and laws. User will not upload any content or material that violates or infringes on any copyright or trademark. User will not transmit any data containing code or programming for any virus, worm, &#8216;Trojan horse&#8217; or any other data which can cause disruption or malfunction to the Service.</p><p>Ownership. User represents that User is at least 18 years of age (or the age of majority in User&#8217;s place of residence). User grants to Drones 4 Hire, LLC, a perpetual, worldwide, nonexclusive, irrevocable, royalty-free, fully sub-licensable, and transferable, right and license to the unlimited use of its uploaded content throughout the universe in and in connection with or relation to the development, marketing, advertisement, order fulfillment, licensing, sale, distribution, and promotion of any services, products, or brands of Drones 4 Hire, LLC. User agrees to waive all moral rights to content uploaded to the website. User agrees that Drones 4 Hire, LLC shall have the right to transform, edit, alter, distort, modify, add to, subtract from, enhance, broadcast, telecast, duplicate, distribute, or otherwise exhibit the content worldwide in all forms of media and forms of exploitation, now known or hereafter created including, but not limited to, websites, film, television, radio, and print. User agrees they will have no right to approve any use of the content. User agrees that no third party has or shall have any right of approval over the use of the content or will be due any amounts from the use of the content. User agrees that this grant of right and license is binding on its heirs, executors, legal representatives, administrators and assigns.</p><p>User releases Drones 4 Hire, LLC from any and all claims and demands that the User may have now or at any time arising from the use of the content, including but not limited to, claims for personal injury, invasion of privacy, defamation, libel, right of publicity, infliction of emotional distress, or additional payment.</p><p>Except as discussed elsewhere in these terms and conditions, all right, title and interest in and to all, trademarks, logos, trade secrets, proprietary information and other intellectual property that now exists or is created or acquired in the future that are used or developed in connection with any services offered by&#160;<a href="https://www.drones4hire.com/preview/">www.drones4hire.com</a>are the sole property of Drones 4 Hire, LLC. User acknowledges those rights and claims no ownership or title in such property.</p><p>Copyright and Trademark Information. All content included or available on this site, excepting content uploaded by User as discussed elsewhere in these Terms, including site design, text, graphics, videos, interfaces, and the selection and arrangements thereof is &#169;2016 Drones 4 Hire, LLC, with all rights reserved. Any use of materials on the website, including reproduction for purposes other than those noted above, modification, distribution, or replication, any form of data extraction or data mining, or other commercial exploitation of any kind, without prior written permission of an authorized officer of Drones 4 Hire, LLC is strictly prohibited. Users agree that they will not use any robot, spider, or other automatic device, or manual process to monitor or copy Drones 4 Hire, LLC&#8217;s web pages or the content contained therein without prior written permission of an authorized officer of Drones 4 Hire, LLC.</p><p>Notification of Claimed Copyright Infringement. Pursuant to Section 512(c) of the Copyright Revision Act, as enacted through the Digital Millennium Copyright Act,<span>&#160;</span><a href="https://www.drones4hire.com/preview/">www.drones4hire.com</a>&#160;designates the following individual as its agent for receipt of notifications of claimed copyright infringement.</p><p><strong>By Mail :</strong></p><p>Drones 4 Hire, LLC</p><p>923 NE Woods Chapel Rd., Ste. 358</p><p>Lee&#8217;s Summit, Missouri, 64064.</p><p>&#160;<strong>By Email :</strong></p><p>admin@drones4hire.com</p><p><strong>&#160;</strong></p><p>Acceptable Use and Content. User warrants that no content posted by User violates any copyright, trademark or intellectual property laws or protections. User agrees that Drones 4 Hire, LLC has no authority or control over content found on the Internet. User accepts full responsibility for content uploaded in connection with User&#8217;s accounts with www.drones4hire.com. User agrees that Drones 4 Hire, LLC has no liability to User or visitors to User&#8217;s content for any preventative or corrective action that may be taken by Drones 4 Hire, LLC to remove any content deemed inappropriate under these terms and conditions.</p><p>Truthfulness in Disclosures. User must provide only truthful information in registration and account creation. Violation of either policy is grounds for immediate termination of User&#8217;s account. User agrees to keep all contact information complete and up to date. User is solely responsible for any content uploaded through User&#8217;s account. Drones 4 Hire, LLC must be notified immediately of any breach of password security or unauthorized use of User&#8217;s account.</p><p>Prohibited Conduct. No User is permitted to engage in any of the following acts:</p><p><strong>(a)</strong>&#160;fail to deliver payment for services delivered to you, unless the Provider has materially changed the Service provided from the bid or a clear typographical error is made;</p><p><strong>(b)</strong>&#160;fail to deliver services purchased from you, unless the Customer fails to meet the terms, materially alters the terms of the Seller Services from the listing;</p><p><strong>(d)</strong>&#160;take any action that may undermine the feedback or reputation systems (such as displaying, importing or exporting feedback information or using it for purposes unrelated to the Drones 4 Hire Website);</p><p><strong>(e)</strong>&#160;transfer your Drones 4 Hire account (including feedback) and Username to another party without our consent;</p><p><strong>(f)</strong>&#160;download and aggregate listings from our website for display with listings from other websites without our express written permission, &#34;frame&#34;, &#34;mirror&#34; or otherwise incorporate any part of the Website into any other website without our prior written authorization;</p><p><strong>(g)</strong>&#160;harvest or otherwise collect information about Users, including email addresses, without their consent.</p><p><strong>(h)</strong>&#160;threaten, stalk, harm, or harass others, or promote bigotry or discrimination;</p><p><strong>(i)</strong>&#160;use the Website&#8217;s messaging system to contact a User for any purposes not directly related to the purchase of services;</p><p><strong>(j)</strong>&#160;conduct your business in a manner that results in or may result in complaints, reversals, chargebacks, fees, fines, or penalties.</p><p>Assignment of Rights and Commercial Use. User may not assign any rights under these terms and conditions. User may only use www.drones4hire.com and related services for the purpose of locating and contracting with businesses providing UAV/drone services unless otherwise provided for under these terms and conditions.</p><p>Privacy. We respect your privacy and are committed to protecting it. Our privacy policy,&#160;<a href="https://www.drones4hire.com/preview/">www.drones4hire.com</a>&#160;governs the processing of all personal data collected from you in connection with your purchase of services through the Site.</p><p>Force Majeure. We will not be liable or responsible to you, nor be deemed to have defaulted or breached these Terms, for any failure or delay in our performance under these Terms when and to the extent such failure or delay is caused by or results from acts or circumstances beyond our reasonable control, including, without limitation, acts of God, flood, fire, earthquake, explosion, governmental actions, war, invasion or hostilities (whether war is declared or not), terrorist threats or acts, riot or other civil unrest, national emergency, revolution, insurrection, epidemic, lockouts, strikes or other labor disputes or materials or telecommunication breakdown or power outage.</p><p>Governing Law and Jurisdiction. All matters arising out of or relating to these Terms are governed by and construed in accordance with the internal laws of the State of Missouri without giving effect to any choice or conflict of law provision or rule (whether of the State of Missouri or any other jurisdiction) that would cause the application of the laws of any jurisdiction other than those of the State of Missouri.</p><p>Dispute Resolution and Binding Arbitration.</p><p><strong>(a)</strong>&#160;YOU AND DRONES 4 HIRE, LLC ARE AGREEING TO GIVE UP ANY RIGHTS TO LITIGATE CLAIMS IN A COURT OR BEFORE A JURY, OR TO PARTICIPATE IN A CLASS ACTION OR REPRESENTATIVE ACTION WITH RESPECT TO A CLAIM. OTHER RIGHTS THAT YOU WOULD HAVE IF YOU WENT TO COURT MAY ALSO BE UNAVAILABLE OR MAY BE LIMITED IN ARBITRATION.</p><p>ANY CLAIM, DISPUTE OR CONTROVERSY (WHETHER IN CONTRACT, TORT OR OTHERWISE, WHETHER PRE-EXISTING, PRESENT OR FUTURE, AND INCLUDING STATUTORY, CONSUMER PROTECTION, COMMON LAW, INTENTIONAL TORT, INJUNCTIVE AND EQUITABLE CLAIMS) BETWEEN YOU AND US ARISING FROM OR RELATING IN ANY WAY TO YOUR PURCHASE OF SERVICES THROUGH THE SITE, WILL BE RESOLVED EXCLUSIVELY AND FINALLY BY BINDING ARBITRATION.</p><p><strong>(b)</strong>&#160;The arbitration will be administered by the American Arbitration Association (&#8221;AAA&#8221;) in accordance with the Consumer Arbitration Rules (the &#8220;AAA Rules&#8221;) then in effect, except as modified by this Section. (The AAA Rules are available at www.adr.org/arb_medor by calling the AAA at 1-800-778-7879.) The Federal Arbitration Act will govern the interpretation and enforcement of this section.</p><p>The arbitrator will have exclusive authority to resolve any dispute relating to arbitrability and/or enforceability of this arbitration provision, including any unconscionability challenge or any other challenge that the arbitration provision or the Agreement is void, voidable or otherwise invalid. The arbitrator will be empowered to grant whatever relief would be available in court under law or in equity. Any award of the arbitrator(s) will be final and binding on each of the parties, and may be entered as a judgment in any court of competent jurisdiction.</p><p>We will be responsible for paying any individual consumer&#8217;s arbitration/arbitrator fees in a dispute with Drones 4 Hire, LLC. We will not pay any arbitration/arbitrator fees for disputes between Buyers and Providers.</p><p><strong>(c)</strong>&#160;You agree to an arbitration on an individual basis. In any dispute, NEITHER YOU NOR DRONES 4 HIRE, LLC WILL BE ENTITLED TO JOIN OR CONSOLIDATE CLAIMS BY OR AGAINST OTHER CUSTOMERS IN COURT OR IN ARBITRATION OR OTHERWISE PARTICIPATE IN ANY CLAIM AS A CLASS REPRESENTATIVE, CLASS MEMBER OR IN A PRIVATE ATTORNEY GENERAL CAPACITY. The arbitral tribunal may not consolidate more than one person&#8217;s claims, and may not otherwise preside over any form of a representative or class proceeding. The arbitral tribunal has no power to consider the enforceability of this class arbitration waiver and any challenge to the class arbitration waiver may only be raised in a court of competent jurisdiction.</p><p>If any provision of this arbitration agreement is found unenforceable, the unenforceable provision will be severed and the remaining arbitration terms will be enforced.</p><p>Liquidated Damages</p><p><strong>(a)</strong>&#160;All parties agree that if a User were to breach this agreement, it would be difficult to determine actual damages;</p><p><strong>(b)</strong>&#160;Based on what information is presently available to the parties, they agree that $5,000.00 is a reasonable estimate of the damages that would accrue if a breach occurred in the future; and</p><p><strong>(c)</strong>&#160;All parties agree that the amount of liquidated damages is fair and reasonable and would not act as a penalty to the breaching party.</p><p>Assignment. You will not assign any of your rights or delegate any of your obligations under these Terms without our prior written consent. Any purported assignment or delegation in violation of this Section is null and void. No assignment or delegation relieves you of any of your obligations under these Terms.</p><p>No Waivers. The failure by us to enforce any right or provision of these Terms will not constitute a waiver of future enforcement of that right or provision. The waiver of any right or provision will be effective only if in writing and signed by a duly authorized representative of Drones 4 Hire, LLC.</p><p>No Third Party Beneficiaries. These Terms do not and are not intended to confer any rights or remedies upon any person other than you.</p><p>Notices.</p><p><strong>(a)</strong>&#160;To You. We may provide any notice to you under these Terms by: (i) sending a message to the e-mail address you provide or (ii) by posting to the Site. Notices sent by e-mail will be effective when we send the e-mail and notices we provide by posting will be effective upon posting. It is your responsibility to keep your e-mail address current.</p><p><strong>(b)</strong>&#160;To Us. To give us notice under these Terms, you must contact us as follows: by personal delivery, overnight courier or registered or certified mail to</p><p><strong>Drones 4 Hire, LLC</strong></p><p><strong>923 NE Woods Chapel Rd., Ste. 358</strong></p><p><strong>Lee&#8217;s Summit, Missouri, 64064.</strong></p><p>We may update the address for notices to us by posting a notice on the Site. Notices provided by personal delivery will be effective immediately. Notices provided by overnight courier will be effective one business day after they are sent. Notices provided by registered or certified mail will be effective three business days after they are sent. Any questions about this User Agreement or to report breaches of this User Agreement may contact use at admin@drones4hire.com.</p><p>Refunds. Refunds are solely at the discretion of Drones 4 Hire, LLC. You may request refunds by emailing refunds@drones4hire.com</p><p>Account Closure and Refusal of Service. Drones 4 Hire, LLC reserves the right to terminate User&#8217;s account or deny service to a User for any reason without notice. User must immediately cease using all services provided under account upon termination. Drones 4 Hire, LLC has the sole right to determine if a User has breached these terms and conditions. Further, Drones 4 Hire, LLC may use any means necessary, including legal action, to terminate User&#8217;s access to&#160;<a href="https://www.drones4hire.com/preview/">www.drones4hire.com</a>&#160;and any related services. User may also request account closure at any time, however an account will not be closed until all outstanding fees have been paid and all service issues resolved.</p><p>Severability. If any provision of these Terms are invalid, illegal, void or unenforceable, then that provision will be deemed severed from these Terms and will not affect the validity or enforceability of the remaining provisions of these Terms.</p><p>Entire Agreement. User agrees that terms and conditions and policies found elsewhere on&#160;<a href="https://www.drones4hire.com/preview/">www.drones4hire.com</a>&#160;represent the entire agreement between User and Drones 4 Hire, LLC.</p><p><strong>THIS CONTRACT CONTAINS A BINDING ARBITRATION PROVISION WHICH MAY BE ENFORCED BY THE PARTIES.</strong></p>', 'en_US');
    
INSERT INTO drones.POLICY (TEXT, LOCALE) VALUES
	('<p><span>Drones 4 Hire, LLC<span class="apple-converted-space">&#160;</span><span class="jbold"><strong><span>(&#8221;Company&#8221; or &#8220;We&#8221;)</span></strong></span><span class="apple-converted-space">&#160;</span>respects your privacy and are committed to protecting it through our compliance with this policy.</span></p><p><span>This policy describes the types of information we may collect from you or that you may provide when you visit the website<span class="apple-converted-space">&#160;</span><a href="https://www.drones4hire.com/preview/"><span>www.drones4hire.com</span></a><span>&#160;</span>(our &#8220;Website&#8221;) and our practices for collecting, using, maintaining, protecting, and disclosing that information.</span></p><p><span><span class="jbold"><strong><span>This policy applies to information we collect:</span></strong></span></span></p><p><span>On this Website.</span></p><p><span>In email, text, and other electronic messages between you and this Website.</span></p><p><span>Through mobile and desktop applications you download from this Website, which provide dedicated non-browser-based interaction between you and this Website.</span></p><p><span>When you interact with our advertising and applications on third-party websites and services, if those applications or advertising include links to this policy.</span></p><p><span>It does not apply to information collected by:</span></p><p><span>us offline or through any other means, including on any other website operated by Company or any third party (including our affiliates and subsidiaries); or</span></p><p><span>any third party (including our affiliates and subsidiaries), including through any application or content (including advertising) that may link to or be accessible from or on the Website</span></p><p><span>Please read this policy carefully to understand our policies and practices regarding your information and how we will treat it. If you do not agree with our policies and practices, your choice is not to use our Website. By accessing or using this Website, you agree to this privacy policy. This policy may change from time to time. Your continued use of this Website after we make changes is deemed to be acceptance of those changes, so please check the policy periodically for updates.</span></p><p><span><span class="jbold"><strong><span>Children Under the Age of 13</span></strong></span></span></p><p><span>Our Website is not intended for children under 13 years of age. No one under age 13 may provide any personal information to or on the Website. We do not knowingly collect personal information from children under 13. If you are under 13, do not use or provide any information on this Website or on or through any of its features/register on the Website, make any purchases through the Website, use any of the interactive or public comment features of this Website or provide any information about yourself to us, including your name, address, telephone number, email address, or any screen name or user name you may use. If we learn we have collected or received personal information from a child under 13 without verification of parental consent, we will delete that information. If you believe we might have any information from or about a child under 13, please contact us at:</span></p><p><span><span class="jbold"><strong><span>Drones 4 Hire, LLC</span></strong></span></span></p><p><span><span class="jbold"><strong><span>923 NE Woods Chapel Rd., Ste. 358</span></strong></span></span></p><p><span><span class="jbold"><strong><span>Lee&#8217;s Summit, Missouri, 64064.</span></strong></span></span></p><p><span><span class="jbold"><strong><span>Information You Provide to Us.</span></strong></span></span><span class="apple-converted-space"><span>&#160;</span></span><span>The information we collect on or through our Website may include:</span></p><p><span>Information that you provide by filling in forms on our Website. This includes information provided at the time of registering to use our Website, subscribing to our service, posting material, or requesting further services. We may also ask you for information when you enter a contest or promotion sponsored by us, and when you report a problem with our Website.</span></p><p><span>Records and copies of your correspondence (including email addresses), if you contact us.</span></p><p><span>Your responses to surveys that we might ask you to complete for research purposes.</span></p><p><span>Details of transactions you carry out through our Website and of the fulfillment of your orders. You may be required to provide financial information before placing an order through our Website.</span></p><p><span>Your search queries on the Website.</span></p><p><span>You also may provide information to be published or displayed (hereinafter,<span class="apple-converted-space">&#160;</span><span class="jbold"><strong><span>&#8220;posted&#8221;</span></strong></span>) on public areas of the Website, or transmitted to other users of the Website or third parties (collectively,<span class="apple-converted-space">&#160;</span><span class="jbold"><strong><span>&#8220;User Contributions&#8221;</span></strong></span>). Your User Contributions are posted on and transmitted to others at your own risk. Although we limit access to certain pages/you may set certain privacy settings for such information by logging into your account profile, please be aware that no security measures are perfect or impenetrable. Additionally, we cannot control the actions of other users of the Website with whom you may choose to share your User Contributions. Therefore, we cannot and do not guarantee that your User Contributions will not be viewed by unauthorized persons.</span></p><p><span><span class="jbold"><strong><span>Information We Collect Through Automatic Data Collection Technologies.</span></strong></span><span class="apple-converted-space"><strong><span>&#160;</span></strong></span></span><span>As you navigate through and interact with our Website, we may use automatic data collection technologies to collect certain information about your equipment, browsing actions, and patterns, including:</span></p><p><span>Details of your visits to our Website including, traffic data, location data, logs, and other communication data and the resources that you access and use on the Website.</span></p><p><span>Information about your computer and internet connection, including your IP address, operating system, and browser type.</span></p><p><span>We also may use these technologies to collect information about your online activities over time and across third-party websites or other online services (behavioral tracking).</span></p><p><span>The information we collect automatically is statistical data and does not include personal information, but we may maintain it or associate it with personal information we collect in other ways or receive from third parties. It helps us to improve our Website and to deliver a better and more personalized service, including by enabling us to:</span></p><p><span>Estimate our audience size and usage patterns.</span></p><p><span>Store information about your preferences, allowing us to customize our Website according to your individual interests.</span></p><p><span>Speed up your searches.</span></p><p><span>Recognize you when you return to our Website.</span></p><p><span><span class="jbold"><strong><span>The technologies we use for this automatic data collection may include:</span></strong></span></span></p><p><span><span class="jbold"><strong><span>Cookies (or browser cookies).</span></strong></span></span><span class="apple-converted-space"><span>&#160;</span></span><span>A cookie is a small file placed on the hard drive of your computer. You may refuse to accept browser cookies by activating the appropriate setting on your browser. However, if you select this setting you may be unable to access certain parts of our Website. Unless you have adjusted your browser setting so that it will refuse cookies, our system will issue cookies when you direct your browser to our Website.</span></p><p><span><span class="jbold"><strong><span>Flash Cookies.</span><span>&#160;</span></strong></span></span><span>Certain features of our Website may use local stored objects (or Flash cookies) to collect and store information about your preferences and navigation to, from, and on our Website. Flash cookies are not managed by the same browser settings as are used for browser cookies.</span></p><p><span><span class="jbold"><strong><span>Web Beacons.</span></strong></span></span><span class="apple-converted-space"><span>&#160;</span></span><span>Pages of our the Website and our e-mails may contain small electronic files known as web beacons (also referred to as clear gifs, pixel tags, and single-pixel gifs) that permit the Company, for example, to count users who have visited those pages or opened an email and for other related website statistics (for example, recording the popularity of certain website content and verifying system and server integrity).</span></p><p><span>We do not collect personal information automatically, but we may tie this information to personal information about you that we collect from other sources or you provide to us.</span></p><p><span><span class="jbold"><strong><span>Third-Party Use of Cookies and Other Tracking Technologies.</span></strong></span></span></p><p><span>Some content or applications, including advertisements, on the Website are served by third-parties, including advertisers, ad networks and servers, content providers, and application providers. These third parties may use cookies alone or in conjunction with web beacons or other tracking technologies to collect information about you when you use our website. The information they collect may be associated with your personal information or they may collect information, including personal information, about your online activities over time and across different websites and other online services. They may use this information to provide you with interest-based (behavioral) advertising or other targeted content.</span></p><p><span>We do not control these third parties&#8217; tracking technologies or how they may be used. If you have any questions about an advertisement or other targeted content, you should contact the responsible provider directly.</span></p><p><span><span class="jbold"><strong><span>How We Use Your Information</span></strong></span></span></p><p><span>We use information that we collect about you or that you provide to us, including any personal information:</span></p><p><span>To present our Website and its contents to you.</span></p><p><span>To provide you with information, products, or services that you request from us.</span></p><p><span>To fulfill any other purpose for which you provide it.</span></p><p><span>To provide you with notices about your account/subscription, including expiration and renewal notices.</span></p><p><span>To carry out our obligations and enforce our rights arising from any contracts entered into between you and us, including for billing and collection.</span></p><p><span>To notify you about changes to our Website or any products or services we offer or provide though it.</span></p><p><span>To allow you to participate in interactive features on our Website.</span></p><p><span>In any other way we may describe when you provide the information.</span></p><p><span>For any other purpose with your consent.</span></p><p><span>We may use the information we have collected from you to enable us to display advertisements to our advertisers&#8217; target audiences. Even though we do not disclose your personal information for these purposes without your consent, if you click on or otherwise interact with an advertisement, the advertiser may assume that you meet its target criteria.</span></p><p><span><span class="jbold"><strong><span>Disclosure of Your Information</span></strong></span></span></p><p><span>We may disclose aggregated information about our users, and information that does not identify any individual, without restriction.</span></p><p><span>We may disclose personal information that we collect or you provide as described in this privacy policy:</span></p><p><span>To our subsidiaries and affiliates.</span></p><p><span>To contractors, service providers, and other third parties we use to support our business and who are bound by contractual obligations to keep personal information confidential and use it only for the purposes for which we disclose it to them.</span></p><p><span>To a buyer or other successor in the event of a merger, divestiture, restructuring, reorganization, dissolution, or other sale or transfer of some or all of Drones 4 Hire, LLC&#8217;s assets, whether as a going concern or as part of bankruptcy, liquidation, or similar proceeding, in which personal information held by Drones 4 Hire, LLC&#8217;s about our Website users is among the assets transferred.</span></p><p><span>To fulfill the purpose for which you provide it. For example, if you give us an email address to use the &#8220;email a friend&#8221; feature of our Website, we will transmit the contents of that email and your email address to the recipients.</span></p><p><span>For any other purpose disclosed by us when you provide the information.</span></p><p><span>With your consent.</span></p><p><span><span class="jbold"><strong><span>We may also disclose your personal information:</span></strong></span></span></p><p><span>To comply with any court order, law, or legal process, including respond to any government or regulatory request.</span></p><p><span>To enforce or apply our terms of use and other agreements, including for billing and collection purposes.</span></p><p><span>If we believe disclosure is necessary or appropriate to protect the rights, property, or safety of Drones 4 Hire, LLC our customers, or others. This includes exchanging information with other companies and organizations for the purposes of fraud protection and credit risk reduction.</span></p><p><span><span class="jbold"><strong><span>Choices About How We Use and Disclose Your Information</span></strong></span></span></p><p><span>We strive to provide you with choices regarding the personal information you provide to us. We have created mechanisms to provide you with the following control over your information:</span></p><p><span><span class="jbold"><strong><span>Tracking Technologies and Advertising.</span></strong></span><span class="apple-converted-space"><strong><span>&#160;</span></strong></span></span><span>You can set your browser to refuse all or some browser cookies, or to alert you when cookies are being sent. To learn how you can manage your Flash cookie settings, visit the Flash player settings page on Adobe&#8217;s<span class="apple-converted-space">&#160;</span><a href="https://www.drones4hire.com/preview/"><span>website</span></a>. If you disable or refuse cookies, please note that some parts of this site may then be inaccessible or not function properly.</span></p><p><span><span class="jbold"><strong><span>Promotional Offers from the Company.</span></strong></span><span class="apple-converted-space"><strong><span>&#160;</span></strong></span></span><span>If you do not wish to have your email address information used by the Company to promote our own or third parties&#8217; products or services, you can opt-out by logging into the Website and adjusting your user preferences in your account profile by turning ON or OFF the relevant line items or by sending us an email stating your request to admin@drones4hire.com. If we have sent you a promotional email, you may send us a return email asking to be omitted from future email distributions. This opt out does not apply to information provided to the Company as a result of a product purchase, warranty registration, product service experience or other transactions.</span></p><p><span><span class="jbold"><strong><span>Targeted Advertising.</span></strong></span><span class="apple-converted-space"><strong><span>&#160;</span></strong></span></span><span>If you do not want us to use information that we collect or that you provide to us to deliver advertisements according to our advertisers&#8217; target-audience preferences, you can opt-out by adjusting your user advertising preferences in your account profile by turning ON or OFF the relevant line item or by sending us an email stating your request to admin@drones4hire.com.</span></p><p><span>We do not control third parties&#8217; collection or use of your information to serve interest-based advertising. However these third parties may provide you with ways to choose not to have your information collected or used in this way. You can opt out of receiving targeted ads from members of the Network Advertising Initiative<span class="apple-converted-space">&#160;</span><span class="jbold"><strong><span>(&#8221;NAI&#8221;)</span></strong></span>on the NAI&#8217;s website.</span></p><p><span><span class="jbold"><strong><span>Accessing and Correcting Your Information</span></strong></span></span></p><p><span>You can review and change your personal information by logging into the Website and visiting your account details page.</span></p><p><span>You may also send us an email at admin@drones4hire.com to request access to, correct or delete any personal information that you have provided to us. We cannot delete your personal information except by also deleting your user account. We may not accommodate a request to change information if we believe the change would violate any law or legal requirement or cause the information to be incorrect.</span></p><p><span>If you delete your User Contributions from the Website, copies of your User Contributions may remain viewable in cached and archived pages, or might have been copied or stored by other Website users. Proper access and use of information provided on the Website, including User Contributions, is governed by our terms of use.</span></p><p><span><span class="jbold"><strong><span>Your California Privacy Rights</span></strong></span></span></p><p><span>California Civil Code Section &#167; 1798.83 permits users of our Website that are California residents to request certain information regarding our disclosure of personal information to third parties for their direct marketing purposes. To make such a request, please send an email to admin@drones4hire.com.</span></p><p><span><span class="jbold"><strong><span>Data Security</span></strong></span></span></p><p><span>We have implemented measures designed to secure your personal information from accidental loss and from unauthorized access, use, alteration, and disclosure. All information you provide to us is stored on our secure servers behind firewalls. Any payment transactions will be encrypted using SSL technology.</span></p><p><span>The safety and security of your information also depends on you. Where we have given you (or where you have chosen) a password for access to certain parts of our Website, you are responsible for keeping this password confidential. We ask you not to share your password with anyone. We urge you to be careful about giving out information in public areas of the Website like message boards. The information you share in public areas may be viewed by any user of the Website.</span></p><p><span>Unfortunately, the transmission of information via the internet is not completely secure. Although we do our best to protect your personal information, we cannot guarantee the security of your personal information transmitted to our Website. Any transmission of personal information is at your own risk. We are not responsible for circumvention of any privacy settings or security measures contained on the Website.</span></p><p><span><span class="jbold"><strong><span>Changes to Our Privacy Policy</span></strong></span></span></p><p><span>It is our policy to post any changes we make to our privacy policy on this page with a notice that the privacy policy has been updated on the Website home page. If we make material changes to how we treat our users&#8217; personal information, we will notify you by email to the primary email address specified in your account or through a notice on the Website home page. The date the privacy policy was last revised is identified at the top of the page. You are responsible for ensuring we have an up-to-date active and deliverable email address for you, and for periodically visiting our Website and this privacy policy to check for any changes.</span></p><p><span><span class="jbold"><strong><span>Contact Information</span></strong></span></span></p><p><span>To ask questions or comment about this privacy policy and our privacy practices, contact us at:</span></p><p><span><span class="jbold"><strong><span>Drones 4 Hire, LLC</span></strong></span></span></p><p><span><span class="jbold"><strong><span>923 NE Woods Chapel Rd., Ste. 358</span></strong></span></span></p><p><span><span class="jbold"><strong><span>Lee&#8217;s Summit, Missouri, 64064.</span></strong></span></span></p>', 'en_US');