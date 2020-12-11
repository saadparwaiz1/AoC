#!/usr/bin/env zsh

if [ "$1" = "second" ]; then
  zsh util/util.zsh strict | wc -l
else
  zsh util/util.zsh | wc -l
fi
