#!/bin/bash
cat << EOS
 Make My Development Environment
EOS

#
# Mac App Store apps install
#
echo " ---- Mac App Store apps -----"
# brew install mas

# APPLE必須
mas install 497799835  # Xcode (8.2.1)
mas install 409183694  # Keynote (6.6.2)
mas install 409201541  # Pages (5.6.2)
mas install 409203825  # Numbers (3.6.2)

# 便利ツール
mas install 425424353  # The Unarchiver (3.11.1)
mas install 803453959  # Slack (2.4.1)
mas install 1024640650 # CotEditor (3.1.2)
mas install 404010395  # TextWrangler (5.5.2)
mas install 1295203466 # Microsoft Remote Desktop 10

# オプション
mas install 485812721  # TweetDeck (3.9.889)
mas install 405399194  # Kindle (1.21.1)
mas install 539883307  # LINE (4.11.1)
echo " ------------ END ------------"
