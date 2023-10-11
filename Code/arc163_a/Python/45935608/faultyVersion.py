def solve():
    n=int(input())
    s=input()
    for k in range(1,n):
        flag = True
        for i in range(min(k,n-k)):
            if s[i]<s[k+i]:
                print("Yes")
                return
            elif s[i]==s[k+i]:
                continue
            else:
                flag = False
                break
        if flag and k<=n-k:
            print("Yes")
            return
    print("No")



for i in range(int(input())):
    solve()