s=input()
count=0
for c in s:
    if(c.isupper()):
        count+=1
if(count>0):
    s=s.upper()
else:
    s=s.lower()
print(s)