let Graphics = (function () {
    let canvas = document.getElementById('id-canvas');
    let context = canvas.getContext('2d');

    function clear() {
        context.clearRect(0, 0, canvas.width, canvas.height);
    }

    function renderTexture() {
        if (myTexture.ready) {
            context.save();
            context.translate(myTexture.center.x, myTexture.center.y);
            context.rotate(myTexture.rotation);
            context.translate(-texmyTextureture.center.x, -myTexture.center.y);
    
            context.drawImage(
                myTexture.image,
                myTexture.center.x - myTexture.width/2,
                myTexture.center.y - myTexture.height/2,
                myTexture.width, myTexture.height);
            
            context.restore();
        }
    }

    return  {
        clear: clear,
        renderTexture: renderTexture
    }
})