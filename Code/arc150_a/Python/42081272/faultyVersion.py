T = int(input())
for t in range(T):
    N,K = map(int,input().split())
    S = "2" + input() + "2"
    S = list(S)
    C = S.count("1")
    
    if C >= 2:
        st = 0
        ed = -1
        ng = 0
        for i in range(len(S)):
            if S[i] == "1":
                ed = i
                if st == 0:
                    st = i
        for i in range(len(S)):
            if S[i] == "?" and st < i and i < ed:
                S[i] = "?"
            elif S[i] == "0" and st < i and i < ed:
                ng = 1
        f = 0
        e = 0
        for i in range(1,st):
            if S[st-i] == "?":
                f += 1
            else:
                break
        for i in range(1 ,len(S)):
            if S[ed+i] == "?":
                e += 1
            else:
                break
        #print(st,ed,e)
        if f and e:
            if f + e + (ed-st+1) == K or ed-st+1 == K:
                pass
            else:
                ng = 1
        elif f == 0 and e == 0:
            
            if ed-st+1 != K:
                ng = 1
                
        else:
            if f + e + (ed-st+1) < K:
                ng = 1
        if ed-st+1 > K:
            ng = 1
        
        #print(st,ed,f,e)
    elif C == 1:
        ng = 0
        if K == 1:
            pass
        else:
            p = 0
            ng = 0
            for i in range(len(S)):
                if S[i] == "1":
                    p = i
            f = 0
            e = 0
            for i in range(1,len(S)):
                if S[p-i] == "?":
                    f += 1
                else:
                    break
            for i in range(1,len(S)):
                if S[p+i] == "?":
                    e += 1
                else:
                    break
            if f and e:
                if f+e+1 != K:
                    ng = 1
            else:
                if f+e+1 < K:
                    ng = 1
            
    else:
        ng = 0
        Box = 0
        cnt = 0
        if S.count("?") < K:
            ng = 1
        for i in range(len(S)):
            if S[i] == "?":
                cnt += 1
            else:
                if cnt > K:
                    ng = 1
                elif cnt == K:
                    if Box:
                        ng = 1
                    else:
                        Box=1
                        cnt = 0
                else:
                    cnt = 0
    if ng:
        print("No")
    else:
        print("Yes")
                
