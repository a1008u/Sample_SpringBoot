# mac-auto-setup
Auto setup system for macOS.

## macOS version
macOS Sierra 10.12.6　
![check shell status](https://img.shields.io/badge/shell-pass-brightgreen.svg)



## Shell structure
|     sh     |    説明    |
| ---------- | ---------- |
|  MyDevEnv  |  homebrew及びbrewに関わるツールをインストールする  |
|  app  　　　|  GUIのappを自動でDLする（MyDevEnvを実行すると実行される）  |
|  appstore  |  appstore内で以前にDLしているappを自動でDLする（MyDevEnvを実行すると実行される）  |


## Downloads

ローカル環境の設定:
```
sh -c "$(curl -fsSL https://raw.githubusercontent.com/a1008u/MyDevEnv/master/MyDevEnv.sh)"
```

bashの設定:
```
sh -c "$(curl -fsSL https://raw.githubusercontent.com/a1008u/MyDevEnv/master/bash/makebash.sh)"
```

zshの設定:
```
sh -c "$(curl -fsSL https://raw.githubusercontent.com/a1008u/MyDevEnv/master/zsh/makezsh.sh)"
```

vimの設定:
```
sh -c "$(curl -fsSL https://raw.githubusercontent.com/a1008u/MyDevEnv/master/vim/makevim.sh)"
```