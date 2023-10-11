s=input()
t=s[1:-1]
ans='No'
if s[0].isupper() and s[-1].isupper():
  if t.isdigit() and 100000<=int(t)<=999999:
    ans='Yes'
print(ans)