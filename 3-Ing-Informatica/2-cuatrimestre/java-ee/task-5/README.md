# Task 5 - JSON Parsing

In this task, we had to get a dataset from [API Open data SO SR](https://slovak.statistics.sk/wps/portal/ext/Databases/Open_data/!ut/p/z1/jZBBbsIwEEVPk61njBvbdGeoCKCoIiAnqTdVUgViFYcoNu31S1F3LSmzG-m9P5oPBkowXfVhD1Wwp646XvYXw1_zdCNnM6oQdTpFtX1iVK75ZL6jUFyBeaKWDyJFlGkS40ot9XaaMYaKgbnHxxuj8D5_BDDj8QWYK6KyLNuleY5JPlngitEEn7VGXIgfYOzF_46swdjakc83R5AIKmOKQnLBGYv5d8Wqq5k8gBmafTM0AzkPl-bbEHr_GGGER-sD2bu-JefO1hXx7xH-pbUnH6D8TUPvtC7Rblwh_Rdmdowp/dz/d5/L2dJQSEvUUt3QS80TmxFL1o2X1ZMUDhCQjFBMDBVTDkwQVJEMzE4SjYyQ1Mx/),
which provides them in JSON format. After this, we had to use a library from Java EE to parse JSON.

This library has 2 ways on parsing JSON files:

- Object Model API: the entire object is loaded into memory, and then we can consult and get info from it.
- Streaming API: we have some methods that we can use to retrieve data from the JSON file on the way
so if the file is too big, we don't need to store it locally.