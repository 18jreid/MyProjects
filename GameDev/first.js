// Globally scoped
var message = "Hello Mom!"

{
    // Scoped by block
    let message1 = "Hello World!"
    console.log(message1)
}

console.log(message)