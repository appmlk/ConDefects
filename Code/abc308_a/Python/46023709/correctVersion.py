S=list(map(int,input().split()))
check_S=sorted(S)
check="True"
check_num=0
for i in range (len(S)):
    if check_num<=S[i] and 100<=S[i]<=675 and S[i]%25==0:
        check_num=S[i]
        
    else:
        check="False"
if check=="True":
    print("Yes")
else:
    print("No")
    