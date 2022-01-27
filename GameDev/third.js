// Array instantiation
let myArray = [];
let colors = ['red', 'green', 'blue'];

// Fastest way to reset an array
// colors.length = 0;
// it is faster than
// colors = [];

// Add elements to array
colors.push('purple')
colors[colors.length] = 'yellow'

console.log(colors[0]);
console.log(colors[1]);
console.log(colors[2]);
console.log(colors[3]);
console.log(colors[4]);

// Multidimensional arrays
let points = [
    [1, 2],
    [3, 4]
];

console.log(points[0][0]);
console.log(points[0][1]);

// Can contain objects
let points2 = [
    {x: 1, y: 2},
    {x: 3, y: 4}
];

console.log(`x: ${points2[0].x}, y: ${points2[0].y}`);

// Can contain different types of objects
let points3 = [
    [0,1],
    {x: 2, y: 3},
    'Objects of array can be different!'
];

// Fastest method to iterate through arrays
console.log('--- for loop ---');
for (let color = 0; color < colors.length; color++) {
    console.log(colors[color])
}

function report(data) {
    console.log('--- for loop ---');
    for (let color = 0; color < data.length; color++) {
        console.log(data[color])
    }
}

report(colors)
delete colors[3];
report(colors)