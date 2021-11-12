CREATE TABLE property(
    capacity int(50) NOT NULL,
    tenantsOccupy int(50) NOT NULL,
    cost int(200) NOT NULL,
    eircode varchar(200) NOT NULL PRIMARY KEY
);

CREATE TABLE tenant (
   id int(50) NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name varchar(200) NOT NULL,
   email varchar(200) NOT NULL,
   phoneNumber varchar(200) NOT NULL,
   eircode varchar(200) NOT NULL,
   FOREIGN KEY (eircode) REFERENCES property(eircode) ON DELETE CASCADE
);