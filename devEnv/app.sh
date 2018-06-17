#!/bin/bash
cat << EOS
 Make My Development Environment
EOS

#
# Install web apps.
#
echo " ----- Install web apps ------"

# 開発共通
brew cask install java
brew cask install eclipse-java
brew cask install sourcetree

# ブラウザ
brew cask install firefox
brew cask install google-chrome

# リモートデスクトップ
brew cask install teamviewer　

# エディタ系
brew cask install atom
brew cask install sublime-text
brew cask install google-japanese-ime

# mac利用アシスタント
brew cask install cheatsheet

# M管理用
brew cask install visual-studio-code
brew cask install intellij-idea-ce
brew cask install postman

# オプション
brew cask install docker
brew cask install virtualbox
brew cask install vagrant
brew cask install funter
brew cask install spotify

echo " ------------ END ------------"

echo " ----- Install font ----------"
brew tap caskroom/fonts && brew cask install font-fira-code
brew cask install caskroom/fonts/font-hack-nerd-font
echo " ------------ END ------------"