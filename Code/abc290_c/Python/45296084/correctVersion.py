n,k=map(int,input().split())
a=sorted(list(set(map(int,input().split()))))
for i in range(min(len(a),k)):
    if i!=a[i]:
        exit(print(i))
print(min(len(a),k))