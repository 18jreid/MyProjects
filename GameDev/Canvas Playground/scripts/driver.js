let canvas = document.getElementById('id-canvas');
let context = canvas.getContext('2d');
let elapsedTime = 0;
let lastTimeStamp = performance.now()

let rotation = 0.0;

let myTexture = {
    imageSrc: 'textures/usulogo.jpg',
    center: { x: 500, y: 500 },
    width: 200,
    height: 200,
    rotation: 0,
    moveRate: 400 / 1000 // 100 pixels per second in ms
};

myTexture.ready = false;
myTexture.image = new Image();
myTexture.image.onload = function() {
    myTexture.ready = true;
};
myTexture.image.src = myTexture.imageSrc;

function onKeyDown(e) {
    //console.log(`${e.key} : ${e.code}`);
    if (e.key === 'a') {
        myTexture.center.x -= myTexture.moveRate * elapsedTime;
    }
    else if (e.key === 'd') {
        myTexture.center.x += myTexture.moveRate * elapsedTime;
    }
    else if (e.key === 'w') {
        myTexture.center.y -= myTexture.moveRate * elapsedTime;
    }
    else if (e.key === 's') {
        myTexture.center.y += myTexture.moveRate * elapsedTime;
    }
}

function update() {
    rotation += Math.PI / 200;
}

function renderTexture(texture) {
    if (texture.ready) {
        context.save();
        context.translate(texture.center.x, texture.center.y);
        context.rotate(texture.rotation);
        context.translate(-texture.center.x, -texture.center.y);

        context.drawImage(
            texture.image,
            texture.center.x - texture.width/2,
            texture.center.y - texture.height/2,
            texture.width, texture.height);
        
            context.restore();
    }
}

function renderShapes() {
    context.save();
    context.translate(canvas.width / 2, canvas.height / 2);
    context.rotate(rotation);
    context.translate(-(canvas.width / 2), -(canvas.height / 2));

    context.strokeStyle = 'rgba(0, 0, 255, 1)';
    context.lineWidth = 1;
    context.strokeRect(
        canvas.width / 4 + 0.5, canvas.height / 4 + 0.5,
        canvas.width / 2, canvas.height / 2);

    context.beginPath();
    context.moveTo(canvas.width / 2, canvas.height / 4);
    context.lineTo(
        canvas.width / 2 + canvas.width / 4,
        canvas.height / 2 + canvas.height / 4);
    context.lineTo(
        canvas.width / 2 - canvas.width / 4,
        canvas.height / 2 + canvas.height / 4);
    context.closePath();

    context.fillStyle = 'rgba(0, 0, 255, 1)';
    context.fill();

    context.lineWidth = 2;
    context.strokeStyle = 'rgba(255, 0, 0, 1)';
    context.stroke();

    context.restore();
}

function render() {
    Graphics.clear

    renderTexture(myTexture);
}

function gameLoop(time) {
    elapsedTime = time - lastTimeStamp
    lastTimeStamp = time

    update();
    render();

    requestAnimationFrame(gameLoop);
}

console.log('game initializing...');
window.addEventListener('keyup', onKeyDown);
requestAnimationFrame(gameLoop); 
