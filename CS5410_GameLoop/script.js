// CS 5410 Assignment 1
// Justin Reid
// A02276642

// initialize onload function and declare time stamp and events lists
window.onload = init;
let lastTime = null;
let events = []
let eventsToReport = []

// Called on open of webpage
function init() {
    // set first time stamp
    lastTime = performance.now();

    // start gameloop with timestamp
    gameLoop(lastTime)
}

// adds an event to the events list from add event button
function addEvent() {
    // retrieve name, interval, and how many times the event will occur
    let name = document.getElementById('eventName').value;
    let interval = document.getElementById('eventInterval').value;
    let times = document.getElementById('eventTimes').value;

    // if labels are empty, declare default
    if (name == "") name = "noName";
    if (interval == "") interval = 1000;
    if (times == "") times = 100;

    // declare the event, along with its important attributes
    let event = {
        name: name,
        interval: interval,
        times: times,
        timeLeftTillNext: interval,
        timeElapsed: 0
    }

    // push event onto events list
    events.push(event)
    eventsToReport.push({
        eventName: event.name,
        eventCount: event.times
    })
}

// goes through all events in events list an updates accordingly
function update(elapsedTime) {
    for (let x = 0; x < events.length; x++) {
        // if events time is out, remove event
        if (events[x].times == 0) {
            events.splice(x, 1)
            continue;
        }

        // update events total time elapsed
        events[x].timeElapsed += elapsedTime;

        // if event time elapsed is greater than the events current change interval, decrement and update time left
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
    // if there are events to display, display them to webpages "console"
    if (eventsToReport.length != 0) {
        let myEvent = eventsToReport.shift();
        let console = document.getElementById("console");
        let event = document.createElement("p");
        event.textContent = "Event: " + myEvent.eventName + " (" + myEvent.eventCount + " Remaining)";
        console.appendChild(event);

        console.scrollTop = console.scrollHeight;
    }
}

// the main game loop for the program, calls update function for game logic to update, then tells game to render
function gameLoop(timeStamp) {
    var elapsedTime = timeStamp - lastTime;
    lastTime = timeStamp;
    update(elapsedTime)
    render()

    requestAnimationFrame(gameLoop)
}