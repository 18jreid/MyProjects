document.title = "My JavaScript Calculator!"

/***
 * Create main Calculator div and all elements
 */
let calculatorDiv = document.createElement('div');
calculatorDiv.setAttribute('class', 'CalculatorDiv');

let calculatorH1 = document.createElement('h1');
calculatorH1.textContent = "JavaScript Calculator";
calculatorDiv.appendChild(calculatorH1);

let p0 = document.createElement('p');
p0.textContent = "Create an expression:";
calculatorDiv.appendChild(p0);

// FIRST NUMBER INPUT
let firstNumInput = document.createElement("input");
firstNumInput.setAttribute('class', 'FirstNum');
calculatorDiv.appendChild(firstNumInput);

// OPERATION BUTTON
let operation = document.createElement("select");
operation.setAttribute('class', 'Operation');

let add = document.createElement("option");
add.textContent = '+';
operation.appendChild(add);

let subtract = document.createElement("option");
subtract.textContent = '-';
operation.appendChild(subtract);

let divide = document.createElement("option");
divide.textContent = '/';
operation.appendChild(divide);

let multiply = document.createElement("option");
multiply.textContent = '*';
operation.appendChild(multiply);

let mod = document.createElement("option");
mod.textContent = '%';
operation.appendChild(mod);

let power = document.createElement("option");
power.textContent = '**';
operation.appendChild(power);

calculatorDiv.appendChild(operation);

// SECOND NUMBER INPUT
let secondNumInput = document.createElement("input");
secondNumInput.setAttribute('class', 'SecondNum');
calculatorDiv.appendChild(secondNumInput);

// COMPUTE BUTTON
let compute = document.createElement("button");
compute.textContent = "Compute";
compute.setAttribute('onclick', 'evaluateExpression(firstNumInput, secondNumInput, operation);');
compute.setAttribute('class', 'Compute');
calculatorDiv.appendChild(compute);

// DIV COLOR BUTTON
let p1 = document.createElement('p');
p1.textContent = "Color of New Result div"
let divColorButton = document.createElement('input');
divColorButton.setAttribute('type', 'color');
divColorButton.setAttribute('class', 'ColorButton');
divColorButton.setAttribute('value', '#39c0fa')
p1.appendChild(divColorButton);
calculatorDiv.appendChild(p1);
document.body.appendChild(calculatorDiv);

function evaluateExpression(firstNum, secondNum, operation) {
    let numOne = eval(firstNum.value);
    let numTwo = eval(secondNum.value);

    if (operation.value === "+") {
        addFunction(numOne, numTwo);
    }
    else if (operation.value === "-") {
        subtractFunction(numOne, numTwo);
    }
    else if (operation.value === "/") {
        divideFunction(numOne, numTwo);
    }
    else if (operation.value === "*") {
        multiplyFunction(numOne, numTwo);
    }
    else if (operation.value === "%") {
        modFunction(numOne, numTwo);
    }
    else if (operation.value === "**") {
        powerFunction(numOne, numTwo);
    }
}

function addFunction(firstNum, secondNum) {
    let num = firstNum + secondNum;

    let statement = firstNum + " + " + secondNum + " = " + num;
    if (!isNaN(num)) {
        addDiv(statement);
    } else {
        errorDiv();
    }
}

function subtractFunction(firstNum, secondNum) {
    let num = firstNum - secondNum;

    let statement = firstNum + " - " + secondNum + " = " + num;
    if (!isNaN(num)) {
        addDiv(statement);
    } else {
        errorDiv();
    }
}

function divideFunction(firstNum, secondNum) {
    let num = firstNum / secondNum;

    let statement = firstNum + " / " + secondNum + " = " + num;
    if (!isNaN(num)) {
        addDiv(statement);
    } else {
        errorDiv();
    }
}

function multiplyFunction(firstNum, secondNum) {
    let num = firstNum * secondNum;

    let statement = firstNum + " * " + secondNum + " = " + num;
    if (!isNaN(num)) {
        addDiv(statement);
    } else {
        errorDiv();
    }
}

function modFunction(firstNum, secondNum) {
    let num = 0;
    if (firstNum > secondNum) {
        num = firstNum % secondNum;
    } else {
        num = secondNum % firstNum;
    }

    let statement = firstNum + " % " + secondNum + " = " + num;
    if (!isNaN(num)) {
        addDiv(statement);
    } else {
        errorDiv();
    }
}

function powerFunction(firstNum, secondNum) {
    let num = Math.pow(firstNum, secondNum);

    let statement = firstNum + " ** " + secondNum + " = " + num;
    if (!isNaN(num)) {
        addDiv(statement);
    } else {
        errorDiv();
    }
}

function addDiv(operationStatement) {
    let operationDiv = document.createElement('div');

    operationDiv.setAttribute('class', 'OperationDiv');
    operationDiv.setAttribute('style', 'background-color: ' + divColorButton.value)
    operationDiv.setAttribute('onclick', 'removeDiv(this);')

    let now = new Date();
    let date = now.getMonth() + "/" + now.getDate() + "/" + now.getFullYear() + " " + now.getHours() + ":" + now.getMinutes()
        + ":" + now.getSeconds() + " ";

    let dateStatement = document.createElement('small');
    dateStatement.setAttribute('class', 'Date');
    dateStatement.textContent = date;

    let statement = document.createElement('small');
    statement.textContent = " ----- " + operationStatement;
    operationDiv.appendChild(dateStatement);
    operationDiv.appendChild(statement);

    document.body.insertAdjacentElement("afterend", operationDiv)
}

function errorDiv() {
    let errorDiv = document.createElement('div');
    errorDiv.setAttribute('class', 'ErrorDiv');
    errorDiv.setAttribute('onclick', 'removeDiv(this);')

    let now = new Date();
    let date = now.getMonth() + "/" + now.getDate() + "/" + now.getFullYear() + " " + now.getHours() + ":" + now.getMinutes()
    + ":" + now.getSeconds() + " ";

    let dateStatement = document.createElement('small');
    dateStatement.setAttribute('class', 'Date');
    dateStatement.textContent = date;

    let statement = document.createElement('small');
    statement.textContent = " ----- " + "Error! Missing One Or More Operands!";
    errorDiv.appendChild(dateStatement);
    errorDiv.appendChild(statement);

    document.body.insertAdjacentElement("afterend", errorDiv)
}

function removeDiv(div) {
    div.remove();
}