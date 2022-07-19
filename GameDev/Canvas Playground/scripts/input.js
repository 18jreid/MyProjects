let Input = (function () {
    function Keyboard() {
        let that =  {
            keys : {}
        };
        function keyPress(e) {
            that.keys[e.key] = e.lastTimeStamp;
        }
        function keyRelease(e) {
            delete that.keys[e.key]
        }
        window.addEventListener('keydown', keyPress);
        window.addEventListener('keyup', keyRelease);
        return that;
    }
    return  {
        Keyboard: Keyboard
    }
})