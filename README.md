module_4_task_1
===============

Cinema tickets booking service is designed for online tickets booking.

- To book list of tickets, you should send POST request with list of tickets in JSON format as request body
  and specify "userId" parameter in the request. Fields "id" and "category" are necessary.
  For example:
  URL: http://localhost:9000/module_4_task_1/cinema/book.json?userId=555
  JSON:
  [
      {
          "id": "10",
          "filmName": "Terminator",
          "filmStartDate": 1381837045452,
          "category": "STANDARD",
          "placeNumber": 1,
          "status": "FREE"
      },
      {
          "id": "11",
          "filmName": "Terminator",
          "filmStartDate": 1381837045452,
          "category": "STANDARD",
          "placeNumber": 2,
          "status": "FREE"
      }
  ]
