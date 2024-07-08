s = input()

lower = 0
capital = 0

for i in range(len(s)):
  if s[i].islower() == True:
    lower = lower+1
  elif s[i].isupper():
    capital = capital + 1

if lower >= capital:
  print(s.lower())
else:
  print(s.upper())