N,M,K = map(int,input().split())
C_List = []
A_list_list = []
R_list = []
for _ in range(M):
    tmp = list(input().split())
    C_List.append(int(tmp[0]))
    R_list.append(tmp[-1])
    tmp_a = tmp[1:-1]
    tmp_a = list(map(int,tmp_a))
    A_list_list.append(tmp_a)

ret = 0
for i in range(2**N):
    for A_list,r  in zip(A_list_list,R_list):
        tmp = 0
        for a in A_list:
            a = a-1
            if(i & 2**a):
                tmp = tmp + 1
        if(((tmp >= K) and (r == "o"))
        or ((tmp < K) and (r == "x"))):
            pass
        else:
            break
    else:
        ret = ret + 1

print(ret)