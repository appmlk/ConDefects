T = int(input())
for _ in range(T):
    K = int(input())
    left = 1
    right = 10**18
    while right - left > 1:
        mid = (left+right)//2
        ans = 0
        mid_s = str(mid)
        N = len(mid_s)
        for i in range(N):
            ans += pow(9,i)
        ans += (int(mid_s[0])-1)*pow(9,N-1)
        bef = int(mid_s[0])
        TF = True
        for i in range(1,N):
            if int(mid_s[i]) > bef:
                ans += (int(mid_s[i])-1)*pow(9,N-1-i)
            elif int(mid_s[i]) == bef:
                ans += int(mid_s[i])*pow(9,N-1-i)-1
                TF = False
                break
            else:
                ans += int(mid_s[i])*pow(9,N-1-i)
            bef = int(mid_s[i])

        if TF and N >= 2 and mid_s[-1] == mid_s[-2]:
            ans -= 1
        if ans < K:
            left = mid
        else:
            right = mid
            
    print(right)