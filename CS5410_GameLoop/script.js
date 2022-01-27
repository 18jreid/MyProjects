// All code written by Justin Reid
// A02276642

window.onload = init;
let lastTime = null;
let events = []
let eventsToReport = []

function init() {
    lastTime = performance.now();
    gameLoop(lastTime)
}

function addEvent() {
    let name = document.getElementById('eventName').value;
    let interval = document.getElementById('eventInterval').value;
    let times = document.getElementById('eventTimes').value;

    if (name == "") {
        name = "noName"
    }
    if (interval == "") {
        interval = 1000
    }
    if (times == "") {
        times = 100
    }

    let event = {
        name: name,
        interval: interval,
        times: times,
        timeLeftTillNext: interval,
        timeElapsed: 0
    }

    events.push(event)
    eventsToReport.push({
        eventName: event.name,
        eventCount: event.times
    })
}

function update(elapsedTime) {
    for (let x = 0; x < events.length; x++) {
        if (events[x].times == 0) {
            events.splice(x, 1)
            continue;
        }

        events[x].timeElapsed += elapsedTime;
        if (events[x].times >= 1) {
            if (events[x].timeElapsed > events[x].timeLeftTillNext) {
                events[x].times -= 1;
                events[x].timeLeftTillNext = events[x].interval
                events[x].timeElapsed = 0
    
                eventsToReport.push({
                    eventName: events[x].name,
                    eventCount: events[x].times
                })
            }
        }
    }
}

function render() {
    if (eventsToReport.length != 0) {
        let myEvent = eventsToReport.shift();
        let console = document.getElementById("console");
        let event = document.createElement("p");
        event.textContent = "Event: " + myEvent.eventName + " (" + myEvent.eventCount + " Remaining)";
        console.appendChild(event);

        console.scrollTop = console.scrollHeight;
    }
}

function gameLoop(timeStamp) {
    var elapsedTime = timeStamp - lastTime;
    lastTime = timeStamp;

    update(elapsedTime)
    render()
    requestAnimationFrame(gameLoop)
}