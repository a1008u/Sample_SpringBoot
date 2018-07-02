#!/bin/bash
cat << EOS
 Make My Development Environment
EOS

# ---------------------------------------
# 必須のインストール
# ---------------------------------------

#
# Install homebrew.
#
echo " --------- Homebrew ----------"
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
brew update
brew upgrade --all --cleanup
brew -v
echo " ------------ END ------------"

#
# Memorize user pass
#
read -sp "Your Password: " pass;

#
# Mac App Store apps install
#
echo " ---- Mac App Store apps -----"
brew install mas
echo " ------------ END ------------"

# ---------------------------------------
# Git用
# ---------------------------------------

#
# Install git
#
echo " ------------ Git ------------"
brew install git
git --version
echo " ------------ END ------------"

# ---------------------------------------
# ターミナルの変更(zshに変更 オプション)
# ---------------------------------------


# echo " ----------- rbenv ------------"
brew install rbenv
# echo " ------------ END ------------"

# echo " ------ fontforge ------"
brew install fontforge
# echo " ------------ END ------------"

# echo " ------------ zsh ------------"
brew install zsh zsh-autosuggestions zsh-completions zsh-syntax-highlighting colordiff
which -a zsh
echo $pass | sudo -S -- sh -c 'echo '/usr/local/bin/zsh' >> /etc/shells'
chsh -s /usr/local/bin/zsh
# echo " ------------ END ------------"

# echo " ------------ Vim ------------"
brew install vim --with-override-system-vi
# echo " ------------ END ------------"

# echo " --------- Powerline ---------"
Font is 14pt Iconsolata for Powerline with Solarized Dark iterm2 colors.
git clone https://github.com/bhilburn/powerlevel9k.git ~/powerlevel9k
git clone https://github.com/powerline/fonts.git ~/fonts
~/fonts/install.sh
# echo " ------------ END ------------"

# echo " ----------- wget ------------"
brew install wget
wget --version
# echo " ------------ END ------------"

# echo " ----------- colorls ------------"
gem install colorls
# echo " ------------ END ------------"


# ---------------------------------------
# テキスト作業効率化
# ---------------------------------------

#
# TeX settings
#
# echo " ------------ TeX ------------"
# brew cask install mactex
# # Tex Live Utility > preference > path -> /Library/TeX/texbin
# version=$(tex -version | grep -oE '2[0-9]{3}' | head -1)
# echo $pass | sudo -S /usr/local/texlive/$version/bin/x86_64-darwin/tlmgr path add
# echo $pass | sudo -S tlmgr update --self --all
# # JPN Lang settings
# cd /usr/local/texlive/$version/texmf-dist/scripts/cjk-gs-integrate
# echo $pass | sudo -S perl cjk-gs-integrate.pl --link-texmf --force
# echo $pass | sudo -S mktexlsr
# echo $pass | sudo -S kanji-config-updmap-sys hiragino-elcapitan-pron
# # Select ==> TeXShop > Preferences > Source > pTeX (ptex2pdf)
# echo " ------------ END ------------"


# ---------------------------------------
# node用
# ---------------------------------------

#
# Install Node.js env
#
echo " ---------- Node.js ----------"
curl -L git.io/nodebrew | perl - setup
nodebrew ls-remote
nodebrew install-binary latest
nodebrew ls
nodebrew use latest
node -v
npm -v
echo " ------------ END ------------"

#
# Install Yarn
#
echo " ----------- Yarn ------------"
brew install yarn
echo " ------------ END ------------"

# ---------------------------------------
# app用(GUIのインストール)
# ---------------------------------------

while true; do
  read -p 'Now install web apps? [Y/n]' Answer
  case $Answer in
    '' | [Yy]* )
      $(cd $(dirname ${BASH_SOURCE:-$0}); pwd)/app.sh
      break;
      ;;
    [Nn]* )
      echo "Skip install"
      break;
      ;;
    * )
      echo Please answer YES or NO.
  esac
done;

# ---------------------------------------
# appstore用(appstoreにあるもののインストール)
# ---------------------------------------

while true; do
  read -p 'Now install App Store apps? [Y/n]' Answer
  case $Answer in
    '' | [Yy]* )
      $(cd $(dirname ${BASH_SOURCE:-$0}); pwd)/appstore.sh
      break;
      ;;
    [Nn]* )
      echo "Skip install"
      break;
      ;;
    * )
      echo Please answer YES or NO.
  esac
done;






