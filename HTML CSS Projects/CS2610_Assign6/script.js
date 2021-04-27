const myApp = Vue.createApp({
    data() {
        return {
            location_JSON_data: undefined,
            location: undefined,

            currentWeatherData: undefined,
            currentTemperature: undefined,
            currentTemperatureHigh: undefined,
            currentTemperatureLow: undefined,
            currentWeatherDescription: undefined,
            currentHumidity: undefined,
            currentPressure: undefined,

            multiDayForecastData: undefined,
            neutral: 40,
            likely: 0,
            unlikely: 0,
        };
    },

    created() {

        fetch('http://api.ipstack.com/check?access_key=ee2062a1e104d1970d5279be79f88e84')
            .then(response => response.json())
            .then(json => {
                this.location_JSON_data = json;
                this.location = "You are located in " + json['city'] + ", " + json['region_name'] + ", " + json['country_name'] +
                    " at coordinates {" + json['latitude'] + ", " + json['longitude'] + "}";

                return fetch(`http://api.openweathermap.org/data/2.5/weather?lat=${json['latitude']}&lon=${json['longitude']}&appid=f3ef08eb804b6a9af7751b81347b1d43&units=imperial`);
            })
            .then(response => response.json())
            .then(json => {
                this.currentWeatherData = json;
                this.currentTemperature = json['main']['temp'];
                this.currentTemperatureHigh = json['main']['temp_max']
                this.currentTemperatureLow = json['main']['temp_min']
                this.currentWeatherDescription = json['weather'][0]['description'];
                this.currentHumidity = json['main']['humidity'];
                this.currentPressure = json['main']['pressure'];

                return fetch(`http://api.openweathermap.org/data/2.5/forecast?lat=${json['coord']['lat']}&lon=${json['coord']['lon']}&appid=f3ef08eb804b6a9af7751b81347b1d43&units=imperial`);
            })
            .then(response => response.json())
            .then(json => {
                this.multiDayForecastData = json['list'];
            });
    },

    methods: {
        toggle(event) {
            let forecastClass = event.target.getAttribute('class');

            if (forecastClass === 'singleDayWeatherNeutral') {
                event.target.setAttribute('class', 'singleDayWeatherLikely');
                this.likely++;
                this.neutral--;
            }
            else if (forecastClass === 'singleDayWeatherLikely') {
                event.target.setAttribute('class', 'singleDayWeatherUnlikely');
                this.unlikely++;
                this.likely--;
            }
            else if (forecastClass === 'singleDayWeatherUnlikely') {
                event.target.setAttribute('class', 'singleDayWeatherNeutral');
                this.neutral++;
                this.unlikely--;
            }
        }
    }
});

const vm = myApp.mount('#myApp');
