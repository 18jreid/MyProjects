const myApp = Vue.createApp({
    data() {
        return {
            location_JSON_data: undefined,
            currentWeatherData: undefined,
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

                return fetch(`http://api.openweathermap.org/data/2.5/weather?lat=${json['latitude']}&lon=${json['longitude']}&appid=f3ef08eb804b6a9af7751b81347b1d43&units=imperial`);
            })
            .then(response => response.json())
            .then(json => {
                this.currentWeatherData = json;

                return fetch(`http://api.openweathermap.org/data/2.5/forecast?lat=${json['coord']['lat']}&lon=${json['coord']['lon']}&appid=f3ef08eb804b6a9af7751b81347b1d43&units=imperial`);
            })
            .then(response => response.json())
            .then(json => {
                this.multiDayForecastData = json['list'];
            });
    },

    methods: {
        toggle(event) {
            let element = event.target;
            if (element.nodeName === "DIV") {
                // Do nothing
            } else if (element.nodeName === "UL") {
                element = element.parentNode;
            } else if (element.nodeName === "LI") {
                element = element.parentNode.parentNode;
            } else if (element.nodeName === "H5") {
                element = element.parentNode;
            }

            if (element.getAttribute('class') === 'singleDayWeatherNeutral') {
                element.setAttribute('class', 'singleDayWeatherLikely');
                this.likely++;
                this.neutral--;
            }
            else if (element.getAttribute('class') === 'singleDayWeatherLikely') {
                element.setAttribute('class', 'singleDayWeatherUnlikely');
                this.unlikely++;
                this.likely--;
            }
            else if (element.getAttribute('class') === 'singleDayWeatherUnlikely') {
                element.setAttribute('class', 'singleDayWeatherNeutral');
                this.neutral++;
                this.unlikely--;
            }
        }
    }
});

const vm = myApp.mount('#myApp');
