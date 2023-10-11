T = int(input())
for _ in range(T):
    N,M,K = map(int,input().split())
    S = input()
    cnt = 0
    lst = [1]
    for i in range(1,len(S)):
        if S[i] == S[i-1]:
            lst[-1] += 1
        else:
            lst.append(1)
    ans = 0
    flag = False
    for i in range(len(lst)-1):
        if not flag:
            ans += 1
            flag = True
        else:
            if lst[i] > 1 and (lst[i+1] > 1 or i+1 == len(lst)-1):
                ans += 1
                flag = True
            else:
                flag = False

    if ans > K:
        print("No")
    else:
        print("Yes")