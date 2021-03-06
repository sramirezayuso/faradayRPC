package ar.edu.itba.faraday.rpc;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A client that performs actions on a Faraday server via its XML-RPC API.
 */
public class FaradayClient {

    /**
     * The RPC Client to be used to perform the API calls.
     */
    private RPCClient client;

    /**
     * Creates a new client with the address of the Faraday server XML-RPC API.
     *
     * @param serverAddress The address of the Faraday server XML-RPC API.
     */
    public FaradayClient(String serverAddress) {
        client = new RPCClient(serverAddress);
    }

    public String createAndAddHost(String name, String os, String category, String update, String oldHostname) {

        return extractId(client.sendMessage("createAndAddHost", name, os, category, update, oldHostname));

    }

    public String createAndAddInterface(String hostId, String name, String mac, String ipv4Address, String ipv4Mask,
                          String ipv4Gateway, List<String> ipv4DNS,  String ipv6Address, String ipv6Prefix,
                          String ipv6Gateway, List<String> ipv6DNS, String networkSegment, List<String> hostnameResolution) {

        return extractId(client.sendMessage("createAndAddInterface", hostId, name, mac, ipv4Address, ipv4Mask,
                ipv4Gateway, ipv4DNS, ipv6Address, ipv6Prefix, ipv6Gateway, ipv6DNS, networkSegment, hostnameResolution));

    }

    public String createAndAddServiceToInterface(String hostId, String interfaceId, String name, String protocol,
                                                 List<String> ports, String status, String version, String description) {

        return extractId(client.sendMessage("createAndAddServiceToInterface", hostId, interfaceId, name, protocol, ports,
                status, version, description));

    }

    public String createAndAddVulnToHost(String hostId, String name, String description,
                                         List<String> refs, String severity) {

        return extractId(client.sendMessage("createAndAddVulnToHost", hostId, name, description, refs, severity));

    }

    public String createAndAddVulnToInterface(String hostId, String interfaceId, String name,
                                              String description, List<String> refs, String severity) {

        return extractId(client.sendMessage("createAndAddVulnToInterface", hostId, interfaceId, name, description, refs, severity));

    }

    public String createAndAddVulnToApplication(String hostId, String applicationId, String name,
                                                String description, List<String> refs, String severity) {

        return extractId(client.sendMessage("createAndAddVulnToApplication", hostId, applicationId, name, description, refs, severity));

    }

    public String createAndAddVulnToService(String hostId, String serviceId, String name,
                                            String description, List<String> refs, String severity) {

        return extractId(client.sendMessage("createAndAddVulnToService", hostId, serviceId, name, description, refs, severity));

    }

    public String createAndAddVulnWebToService(String hostId, String serviceId, String name,
                                               String description, List<String> refs, String severity,
                                               String website, String path, String request, String response,
                                               String method, String pname, String params, String query, String category) {

        return extractId(client.sendMessage("createAndAddVulnWebToService", hostId, serviceId, name, description, refs, severity,
                website, path, request, response, method, pname, params, query, category));

    }

    public String createAndAddNoteToHost(String hostId, String name, String text) {
        return extractId(client.sendMessage("createAndAddNoteToHost", hostId, name, text));

    }

    public String createAndAddNoteToInterface(String hostId, String interfaceId, String name, String text) {
        return extractId(client.sendMessage("createAndAddNoteToInterface", hostId, interfaceId, name, text));
    }

    public String createAndAddNoteToApplication(String hostId, String applicationId, String name, String text) {
        return extractId(client.sendMessage("createAndAddNoteToApplication", hostId, applicationId, name, text));
    }

    public String createAndAddNoteToService(String hostId, String serviceId, String name, String text) {
        return extractId(client.sendMessage("createAndAddNoteToService", hostId, serviceId, name, text));
    }

    public String createAndAddNoteToNote(String hostId, String serviceId, String noteId, String name, String text) {
        return extractId(client.sendMessage("createAndAddNoteToNote", hostId, serviceId, noteId, name, text));
    }

    public String createAndAddCredToService(String hostId, String serviceId, String username, String password) {
        return extractId(client.sendMessage("createAndAddCredToService", hostId, serviceId, username, password));
    }

    public void devlog(String message) {
        client.sendMessage("devlog", message);
    }

    private String extractId(String rpcResponse) {
        String pattern = "<value><string>(.+?)</string></value>";

        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(rpcResponse);
        matcher.find();

        return matcher.group(1);
    }
 }
