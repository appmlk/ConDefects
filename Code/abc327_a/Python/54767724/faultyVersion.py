n = int(input())
s = input()
for i in range(n-1):
  if s[i]+s[i+1] == "ab":
    print("Yes")
    break
else:
  print("No")