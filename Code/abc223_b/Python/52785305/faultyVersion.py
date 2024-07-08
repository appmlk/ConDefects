s = list(input())
ans = []
for i in range(len(s)):
  ans.append(s[i:len(s)]+s[0:i])
print(min(ans))
print(max(ans))