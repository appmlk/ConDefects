n=int(input())
s=list(input())
if "".join(s)=="AB":
    print("No")
    exit()
if s[0]=="B":
    print("Yes")
    exit()
if s[0]=="A" and s[-1]=="B":
    print("No")
    exit()
print("Yes")