const uuid = require("uuid");
const loadtest = require("loadtest");
const axios = require("axios");

const description = [
  "Manager",
  "Developer",
  "Scrum-Master",
  "Architect",
  "Product-Owner",
  "Vice-President",
  "Associate-Developer",
];

function randomIntFromInterval(min, max) {
  // min and max included
  return Math.floor(Math.random() * (max - min + 1) + min);
}

const userSignup = async(username=uuid.v4(), password=uuid.v4()) => {
  const response = await axios.post('http://localhost:8080/users/signup', {username, password});
  return response.data.accessToken;
}

const getRandomDescription = () => {
  return description[randomIntFromInterval(0, description.length - 1)];
};

function generateEmployee() {
  const eventName = uuid.v4();
  const eventDescription = getRandomDescription();
  const eventDate = new Date().toISOString();
  const design = uuid.v4();
  const sharableLink = uuid.v4();
  const scope = uuid.v4();

  return {
    eventDate,
    eventName,
    eventDescription,
    design,
    sharableLink,
    scope
  };
}

const regions = [
  {
    url: "http://localhost:8080",
    region: "IND",
  },
];

const regionToTest = regions;

(async() => {
  const accessToken = await userSignup();

  console.log(accessToken);

  const options = regionToTest.map((region) => {
    return {
      url: "http://localhost:8080/eventObjects",
      concurrency: 10,
      method: "POST",
      body: generateEmployee(),
      headers: {
        "Authorization": "Bearer "+accessToken
      },
      maxRequests: 2000,
      contentType: "application/json",
    };
  });

  options.forEach(option => loadtest.loadTest(option, (error, results) => {
    if (error) {
      return console.error("Got an error: %s", error);
    }
    console.log(results);
    console.log("Tests run successfully");
  }));

})();
