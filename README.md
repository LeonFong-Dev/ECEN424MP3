# ECEN 424 — MP3 TCP Sockets in Java (starter)

This is a **scaffold, not a completed assignment**. The `TODO` comments identify what needs to be implemented. The server/client programs do not exchange data yet.

## Work split

| Person | Files | Responsibilities |
| --- | --- | --- |
| **Leon (you)** | `Server.java`, `ClientHandler.java` | Parse server arguments; prompt for `str` and positive `N`; accept connections; handle up to `max_clients` simultaneously; send `str` N times, 1 second apart, newline on final send; track/release active-client slots. |
| **Karthik** | `NaiveClient.java`, `BufferClient.java` | Parse server IP/port; connect with TCP; print characters immediately in NaiveClient; buffer until newline in BufferClient; terminate after newline; lead integration testing. |
| **Both** | All files | Compile, test both client types and concurrent clients; handle edge cases and verify the output. |

## Agreed communication behavior

- Server sends the exact chosen `str` N times over each connection.
- No newline at the end of the first N-1 transmissions; **one newline on the final transmission**.
- A one-second pause occurs **between** sends, not necessarily after the final send.
- TCP is a **byte stream**: reads do not necessarily match individual writes.
- Clients terminate after receiving **and printing** the newline.
- The maximum simultaneously active client count is distinct from the OS connection backlog.

## File organization

```text
ECEN424_MP3_Starter/
├── Server.java         # Leon
├── ClientHandler.java  # Leon
├── NaiveClient.java    # Karthik
├── BufferClient.java   # Karthik
└── README.md
```

## Compile

From this directory:

```bash
javac *.java
```

On PowerShell, the same command works if Java is installed and on PATH.

## Run (once TODOs are implemented)

Open **separate terminals**:

Terminal 1 — server:

```bash
java Server 5000 3
```

Enter an example string such as `Hi` and a positive integer such as `3` when prompted.

Terminal 2 — naive client:

```bash
java NaiveClient 127.0.0.1 5000
```

Terminal 3 — buffered client:

```bash
java BufferClient 127.0.0.1 5000
```

**Note:** The MP3 handout uses `./server <server_port> <max_clients>` and `./client <server_ip> <server_port>` as illustrative executable forms. For plain Java classes, use `java ClassName ...` unless your instructor specifies a wrapper or executable naming convention.

## Checkpoints

- [ ] The server launches and listens on the chosen port.
- [ ] The server prompts for `str` and a positive `N`.
- [ ] A single NaiveClient receives/prints bytes as they arrive.
- [ ] A single BufferClient displays nothing until newline arrives.
- [ ] Clients terminate after printing the newline.
- [ ] Multiple clients work **concurrently**, not sequentially.
- [ ] No more than `max_clients` are actively served at once.
- [ ] Active-client capacity is restored when a handler finishes or fails.

Source: ECEN 424 MP3, "TCP Sockets in Java," pages 1–2.
