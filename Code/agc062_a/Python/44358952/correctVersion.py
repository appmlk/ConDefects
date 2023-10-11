t = int(input())
ans = []
for _ in range(t):
  n = int(input())
  s = input()
  if s[-1] == "A":
    ans.append("A")
  else:
    if all(i == "B" for i in s):
      ans.append("B")
    elif any((s[i] == "B" and s[i+1] == "A") for i in range(n-1)):
      ans.append("A")
    else:
      ans.append("B")
print("\n".join(ans))