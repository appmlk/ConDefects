N = int(input())
num_set = set(range(1, 2*N+2))
while True:
    print(num_set.pop, flush=True)
    
    a = int(input()) #aは青木君が宣言した数
    if a==0:
        break
    num_set.discard(a)