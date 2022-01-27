let person = {
    firstName: 'Justin',
    lastName: 'Reid',
    age: 21,
    allData: function() {
        return this.firstName + " " + this.lastName + " is " + this.age + " years old!";
    }
};

console.log(person.allData());