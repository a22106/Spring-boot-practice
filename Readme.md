## Install Redis on Windows

```bash
# On powershell
wsl --install Ubuntu-22.04 # install Ubuntu 22.04
```

```bash
sudo apt-get update
sudo apt-get install redis-server # install redis-server

redis-server --version # check version
redis-cli # start redis-cli
# When redis-cli is running type ping to check if it is working
# If it is working it will return PONG 
```