let canvas = document.getElementById("id-canvas");
let context = canvas.getContext('2d');

function update() {

}

function render() {
    context.strokeStyle = 'rgba(0,0,255,1)'

    context.lineWidth = 2;
    context.strokeRect(
        canvas.width / 4 + 0.5, canvas.height / 4 + 0.5,
        canvas.width / 2, canvas.height / 2);

}

function gameLoop(time) {
    update();
    render();

    requestAnimationFrame(gameLoop);
}

gameLoop(performance.now())