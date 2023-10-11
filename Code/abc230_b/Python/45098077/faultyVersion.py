s = input()
now = -1
ans = "Yes"
for i in range(len(s)):
  if s[i] == 'o':
    if (now == -1 and i >= 3) or (now != -1 and i != now+3):
      ans = "No"
    now = i
print(ans)
