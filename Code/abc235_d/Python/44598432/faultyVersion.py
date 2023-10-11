from queue import Queue
a,N=map(int,input().split())
def rot(N):
    M=str(N)
    return int(M[1:]+M[0])
d=[10**3]*10**len(str(N))
d[N]=0
q=Queue()
q.put(N)
while not q.empty():
    M=q.get()
    if M==1:
        break
    if M%a==0 and d[M//a]>d[M]+1:
        d[M//a]=d[M]+1
        q.put(M//a)
    if d[rot(M)]>d[M]+1:
        d[rot(M)]=d[M]+1
        q.put(rot(M))
print(d[1] if d[1]!=10**3 else -1)