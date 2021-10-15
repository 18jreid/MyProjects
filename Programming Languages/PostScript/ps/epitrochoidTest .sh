cat epitrochoid.ps > temp.ps
tail -n +2 epitrochoidTest.ps >> temp.ps
gs -f temp.ps
