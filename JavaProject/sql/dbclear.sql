DELETE
FROM SummarySheets
WHERE true;
DELETE
FROM Events
WHERE true;
DELETE
FROM KitchenProcesses
WHERE true;
DELETE
FROM MenuItems
WHERE true;
DELETE
FROM MenuSections
WHERE true;
DELETE
FROM MenuFeatures
WHERE true;
DELETE
FROM Menus
WHERE true;
DELETE
FROM Roles
WHERE true;
DELETE
FROM Services
WHERE true;
DELETE
FROM ShiftCooks
WHERE true;
DELETE
FROM Shifts
WHERE true;
DELETE
FROM Tasks
WHERE true;
DELETE
FROM UserRoles
WHERE true;
DELETE
FROM Users
WHERE true;

DROP TABLE userroles;
DROP TABLE tasks;
DROP TABLE kitchenprocesses;
DROP TABLE events;
DROP TABLE menufeatures;
DROP TABLE menuitems;
DROP TABLE menus;
DROP TABLE menusections;
DROP TABLE roles;
DROP TABLE summarysheets;
DROP TABLE services;
DROP TABLE shiftcooks;
DROP TABLE shifts;
DROP TABLE users;