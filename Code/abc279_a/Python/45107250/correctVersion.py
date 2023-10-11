def string(s):
  if 1<=len(s)<=100:
    bot=0
    for i in s:
      if i=='w':
        bot+=2
      elif i=='v':
        bot+=1
    return bot
print(string(input()))