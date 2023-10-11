N, M = map(int, input().split())
p = list(map(int, input().split())) if M > 0 else []

graph = ""
for i in range(1, N+1):
    node = str(i)
    graph += node + ("v" if i in p else ",")

result = []
for subgraph in graph.split(","):
    result += sorted(list(map(int, subgraph.split("v"))), reverse=True) if len(subgraph) > 0 else []

print(*result)
